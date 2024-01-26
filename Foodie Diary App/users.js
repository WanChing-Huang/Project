const users = {
  
};


function isValidUsername(username) {
    let isValid = true;
    isValid = isValid && username.trim();
    isValid = isValid && username.match(/^[A-Za-z0-9_]+$/);
    return isValid;
}


function getUserData(username) {
    return users[username];
  }
  
function addUserData(username, userData) {
    users[username] = userData;

}

function getUsers(){
    return users;
}

function getAllPosts(){
    return Object.values(users);
}

module.exports = {

    isValidUsername,
    addUserData,
    getUserData,
    getAllPosts,
     getUsers,


};




