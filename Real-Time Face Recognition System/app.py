import cv2
import numpy as np
import os
from tensorflow.keras.models import load_model
from collections import deque

def ensure_dir(directory):
    if not os.path.exists(directory):
        os.makedirs(directory)
        print(f"Created directory: {directory}")

def init_directories():
    ensure_dir("known_faces")
    ensure_dir("unknown_faces")

model = load_model('face_recognition_model.keras')
face_cascade = cv2.CascadeClassifier(cv2.data.haarcascades + 'haarcascade_frontalface_default.xml')

known_face_names = []
known_face_encodings = []

def load_known_faces():
    known_faces_dir = "known_faces"
    print("Loading known faces...")
    for filename in os.listdir(known_faces_dir):
        if filename.endswith(".jpg"):
            name = os.path.splitext(filename)[0]
            img_path = os.path.join(known_faces_dir, filename)
            img = cv2.imread(img_path)
            if img is not None:
                face = preprocess_face(img)
                encoding = model.predict(face)
                known_face_encodings.append(encoding)
                known_face_names.append(name)
    print(f"Loaded {len(known_face_names)} faces.")

def preprocess_face(face):
    # Resize the face image to the input shape expected by the model (62x47)
    face = cv2.resize(face, (62, 47))
    # Normalize pixel values to be between 0 and 1
    face = face / 255.0
    # Expand dimensions to match the input shape for the model (1, 62, 47, 3)
    return np.expand_dims(face, axis=0)


def cosine_similarity(a, b):
    return np.dot(a, b) / (np.linalg.norm(a) * np.linalg.norm(b))

def recognize_face_from_camera():
    video_capture = cv2.VideoCapture(0)
    if not video_capture.isOpened():
        print("Cannot open camera.")
        return

    unknown_counter = 0
    unknown_faces = []
    face_recognition_history = {}
    window_size = 10

    while True:
        ret, frame = video_capture.read()
        if not ret:
            print("Cannot read frames.")
            break

        gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
        faces = face_cascade.detectMultiScale(gray, scaleFactor=1.1, minNeighbors=5, minSize=(30, 30))

        for (x, y, w, h) in faces:
            face = frame[y:y+h, x:x+w]
            processed_face = preprocess_face(face)
            face_encoding = model.predict(processed_face).flatten()

            if len(known_face_encodings) > 0:
                similarities = [cosine_similarity(face_encoding, known_enc.flatten()) for known_enc in known_face_encodings]
                best_match_index = np.argmax(similarities)
                max_similarity = similarities[best_match_index]

                if max_similarity > 0.7:  # 调整此阈值
                    name = known_face_names[best_match_index]
                else:
                    name = "Unknown"
            else:
                name = "Unknown"

            if name == "Unknown":
                matched = False
                for i, (unknown_face, unknown_name) in enumerate(unknown_faces):
                    similarity = cosine_similarity(face_encoding, unknown_face)
                    if similarity > 0.8:  # 为未知人脸设置更严格的阈值
                        name = unknown_name
                        matched = True
                        break
                
                if not matched:
                    name = f"Unknown_{unknown_counter}"
                    cv2.imwrite(f"unknown_faces/{name}.jpg", face)
                    unknown_faces.append((face_encoding, name))
                    unknown_counter += 1

            # 使用滑动窗口平均来稳定识别结果
            face_key = f"{x}_{y}_{w}_{h}"
            if face_key not in face_recognition_history:
                face_recognition_history[face_key] = deque(maxlen=window_size)
            face_recognition_history[face_key].append(name)

            # 获取最频繁出现的名字
            stable_name = max(set(face_recognition_history[face_key]), key=face_recognition_history[face_key].count)

            color = (0, 255, 0) if stable_name in known_face_names else (0, 0, 255)
            cv2.rectangle(frame, (x, y), (x+w, y+h), color, 2)
            cv2.putText(frame, f"{stable_name}", (x, y-10), cv2.FONT_HERSHEY_SIMPLEX, 0.9, color, 2)

        cv2.imshow('Video', frame)

        if cv2.waitKey(1) & 0xFF == ord('q'):
            break

    video_capture.release()
    cv2.destroyAllWindows()

if __name__ == "__main__":
    init_directories()
    load_known_faces()
    model.summary()
    recognize_face_from_camera()