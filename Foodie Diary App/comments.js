const uuid = require('uuid').v4;


function makeCommentList() {
 
  const commentList = {};
  const comments = {
   //place to store comment
  };

  commentList.getComments = function getComments() {
    return comments;
  };

  commentList.addComment = function addComment(username,text) {
    const id = uuid();
    comments[id] = {
      id,
      username,
      text,
    };
    return id;
  };
  
  commentList.getCommentById = function getCommentById(id) {
    return comments[id];
  };

  
  commentList.getCommentByUsername = function getCommentsByUsername(username) {
    return Object.values(comments).filter(comment => comment.username === username);
  }; 


  return commentList;
}

module.exports = {
  makeCommentList,
};
