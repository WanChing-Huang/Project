

const uuid = require('uuid').v4;

const comments = require('./comments')

function makePostList() {
 
  const postList = {};
  const posts = {
   //posts obj to save posts
  };


  postList.getPosts = function getPosts() {
    return posts;
  };

  postList.addPost = function addPost(img,title,address,text) {
    const id = uuid();
    const comment = comments.makeCommentList();

    posts[id] = {
      id : id,
      img,
      title,
      address,
      text,
      commentList: comment.getComments(),
      comment: comment,
    };

    return id;
  };
  
  postList.getPostById = function getPostById(id) {
    return posts[id];
  };

  postList.deletePostById = function deletePostById(id){
    delete posts[id];
  };

  postList.addCommentByPostId =function addCommentByPostId(postId,username,text){
    
    const commentId =  posts[postId].comment.addComment(username,text);
    
    return commentId;
  }

  return postList;
}

module.exports = {
  makePostList,
};
