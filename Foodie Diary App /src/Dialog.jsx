import "./Dialog.css";

import { useRef } from "react";
import { useState } from "react";
import { LOGIN_STATUS } from './constants.js';

function Dialog({item, loginStatus, onComment, id}) {
    const dialogRef = useRef();


    const [isShow, setIsShow] = useState(false);
    const [comment, setComment] = useState("");
    const [ postId, setPostId ] = useState("");

    const commentList = item.commentList;
    
    

    function onChange(e) {
        setComment(e.target.value);
    }

    function onSubmit(e) {
        setPostId(id);
        e.preventDefault();
        if (comment, postId) {
            onComment(comment,postId);
             //get id and add tp 
        }

    }

    
    const commentInput = loginStatus === LOGIN_STATUS.IS_LOGGED_IN && isShow === true?
        <form className='comment_form' action='#/comment' onSubmit={onSubmit}  method='PATCH' >
            
            <textarea className="comment_textarea" rows="2" cols="30" onChange={onChange} placeholder="Comment here..."> 
            </textarea>
            <button className="comment__button" type="submit" >Send</button>
        </form>
        : "";
    
    
        const comments = Object.values(commentList).map(comment =>{
            return(
                <li className="comment-list_comment" key={comment.id}>
                    <span className="comment-list_username">{comment.username}</span>
                    <p className="comment-list_content">: {comment.text}</p>
                </li>
        ) })

    return (
        <>
            <button className="dialog_button-open" onClick={() =>{dialogRef.current.showModal(); setIsShow(!isShow);}}>More Detail</button>
            <dialog className="dialog" ref={dialogRef}>
                <div className="card">
                <h1 className='card__title'>{item.title}</h1>
            <img className='card__pic' src={item.img} alt={item.alt} />     
                <p className='card__text'>{item.text}</p>
                <h3 className="dialog_header">Comments:</h3>
                <ul className="comment-list">
                {comments}
                </ul> 
             {commentInput}  
            < button className="form-close"
                    onClick={() => {
                        dialogRef.current.close()
                         setIsShow(false);}}>close</button></div>
            </dialog>
        </>
    )



}

export default Dialog;