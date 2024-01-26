const express = require('express');
const cookieParser = require('cookie-parser');
const multer = require('multer');
const path = require('path');

const app = express();
const PORT = 3000;

const sessions = require('./sessions');
const users = require('./users');
const posts = require('./posts');



app.use(cookieParser());
app.use(express.static('./dist'));
app.use(express.json());


//initial some sample data

const list = posts.makePostList();

users.addUserData('Wendy', list);
const post1 = list.addPost('picture/834C882A-7410-41DB-A5DE-AF49F191F092.jpg', 'Seattle best Hotpot', 'Seattle', "The sizzling sound as ingredients meet the hot broth, releasing their flavors into the communal cauldron, is a symphony of culinary delight. Hotpot not only satisfies the taste buds but also fosters a shared, convivial atmosphere where friends and family gather around the steaming pot, creating lasting memories. Whether it's the spicy kick of a Sichuan hotpot or the soothing warmth of a classic broth, the allure of hotpot lies not just in its flavors, but in the communal joy it brings to the table.");
const wendyComment1 = list.addCommentByPostId(post1, 'Cathy', 'yammy good place to gather together');

const list2 = posts.makePostList();
users.addUserData('Cathy', list2);
const post2 = list2.addPost('picture/IMG_8793.JPG', 'Seattle best tapas', 'Seattle 222', "The idea of diving into a mix of small, tasty bites with tapas is like a flavor adventure waiting to happen. I can almost imagine the zing of olives, the kick of chorizo, and the comfort of patatas bravas. Then there's the seafood plate, promising a sea-inspired feast with juicy shrimp, tender calamari, and maybe even some grilled octopus. It's like a culinary trip to the ocean on a plate. Just thinking about it makes me crave the delightful blend of Spanish goodness, and the shared joy of digging into these dishes with friends or family. Can't wait to turn these tasty thoughts into a reality!");
const cathyComment = list2.addCommentByPostId(post2, 'Wendy', 'Good Good!!');



//if not login in 
app.get('/api/cards', (req, res) => {

  const cards = users.getAllPosts().map(post => (post.getPosts())).map(com => Object.values(com));
  const target = cards.flat(2).flatMap(innerArray => innerArray);
  
  res.json(target);
}

)


app.get('/api/myCards', (req, res) => {
  
  const sid = req.cookies.sid;
  const username = sid ? sessions.getSessionUser(sid) : '';
  
  if (!sid || !username) {
    return;
  }
  
  if(!users.getUserData(username)){
    return;
  }

  const data = users.getUserData(username).getPosts();

  res.json(Object.values(data));
}
)

//get if login or not 
app.get('/api/session', (req, res) => {
  const sid = req.cookies.sid;
  const username = sid ? sessions.getSessionUser(sid) : '';


  if (!sid || !username) {
    res.json({ loginStatus: 'notLoggedIn', username });
    return;
  }

  res.json({ loginStatus: 'loggedIn', username });
});

//login

app.post('/api/session', (req, res) => {
  const { username } = req.body;
  if (!users.isValidUsername(username)) {
    res.status(400).json({ error: 'required-username' });
    return;
  }

  if (username === 'dog') {
    res.status(403).json({ error: 'auth-insufficient' });
    return;
  }

  //set sessions
  const sid = sessions.addSession(username);
  
  //set userData
  

  res.cookie('sid', sid);
  //send back 

  res.json({ username });

});



app.delete('/api/session', (req, res) => {
  const sid = req.cookies.sid;
  const username = sid ? sessions.getSessionUser(sid) : '';

  if (sid) {
    res.clearCookie('sid');
  }

  if (username) {
    // Delete the session, but not the user data
    sessions.deleteSession(sid);
  }


  res.json({ loginStatus: 'notLoggedIn' });
});

//-------------------------------------------

// Set up multer to handle file uploads

const storage = multer.diskStorage({
  destination: function (req, file, cb) {
    cb(null, path.join(__dirname, 'dist', 'picture')); // Save uploaded files to the 'public/picture' directory
  },
  filename: function (req, file, cb) {
    cb(null, file.fieldname + '-' + Date.now() + path.extname(file.originalname));
  },
});

const upload = multer({ storage: storage });

app.patch('/api/post',upload.single('avatar'), (req, res) => {
  const sid = req.cookies.sid;
  const username = sid ? sessions.getSessionUser(sid) : '';

  if (!sid || !username) {
    res.status(401).json({ error: 'auth-missing' });
    return;
  }

  if (!req.file) {
    return res.status(400).send({ error:'No file uploaded.'});
  }


  const data = req.body;
  const imageSrc = `picture/${req.file.filename}`;

  if(!users.getUserData(username)){
    const listToAdd = posts.makePostList();
    users.addUserData(username,listToAdd); 
  }
  
  const list1 = users.getUserData(username);
  const post = list1.addPost(imageSrc,data.title,data.address,data.text);
  
  const userPosts = Object.values(list1.getPosts());
  
  res.json({ successMessage: 'Upload succeed!!' });
});


//add comment

app.patch('/api/comment', (req, res) => {
  const sid = req.cookies.sid;
  const username = sid ? sessions.getSessionUser(sid) : '';
  
  const { comment, id } = req.body;

  if (!sid || !username) {
    res.status(401).json({ error: 'auth-missing' });
    return;
  }
  //get all posts obj array
  const allPosts = users.getAllPosts().map(post => (post.getPosts())).map(com => Object.values(com)).flat(2).flatMap(innerArray => innerArray);


  const toAdd= allPosts.find(item =>item.id == id);
  const toAddComment= toAdd.comment.addComment(username,comment);

  res.json({ successMessage: 'Upload succeed!!' });

});


app.delete('/api/cards', (req,res) =>{
  const sid = req.cookies.sid;
  const username = sid ? sessions.getSessionUser(sid) : '';

  if (!sid || !username) {
    res.status(401).json({ error: 'auth-missing' });
    return;
  }

  const {id} = req.body;
   
  const data = users.getUserData(username).deletePostById(id);
  res.json({ successMessage: 'Post Deleted!!' });
});

app.listen(PORT, () => console.log(`http://localhost:${PORT}`));