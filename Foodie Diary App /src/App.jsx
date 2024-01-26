import { useState, useEffect } from 'react'

import './App.css'

import { LOGIN_STATUS } from './constants.js';
import { fetchCards, fetchSession, fetchLogin, fetchUpPost,fetchUpComment,fetchMyCards,fetchLogout ,fetchDeleteCard} from './service';

import LoginForm from './LoginForm';
import Home from './Home';
import MenuBar from './MenuBar';
import AddPostForm from './AddPostForm';
import MyPage from './MyPage.jsx';

function App() {

  const [ error, setError ] = useState('');
  const [ username, setUsername] = useState('');
  const [ loginStatus, setLoginStatus] = useState(LOGIN_STATUS.NOT_LOGGED_IN);
  const [ isPagePending, setIsPagePending ] = useState(false);
  const [ page, setPage ] = useState('Home');
  const [ data, setData] = useState([]);
  const [ userPosts,setUserPosts]= useState([]);
  const [ message, setMessage ] = useState('');


  
  function checkSession(){
    setMessage('');
    setError('');
      fetchSession().
      then( data =>{
        setLoginStatus(data.loginStatus);
        setUsername(data.username);
        return fetchCards();
      }

      ).catch( err => {
      setError(err?.error || 'ERROR');
    })
     .then(posts=>{
       setData(posts);
       return fetchMyCards();
      //fetch to refresh the mycards data
   }).
    then(
      data =>{
        if(data){
        setUserPosts(data); }
      }
    
   );
    
  }

  function onLogin( username ) {
    setError('');
    setIsPagePending(true);
    fetchLogin(username)
    .then(
      session =>{
      setUsername(session.username);
      setLoginStatus(LOGIN_STATUS.IS_LOGGED_IN);
      setIsPagePending(false);
      setPage('MyPage');
      return fetchMyCards();
      }
    )
    .catch( err => {
      setError(err?.error || 'ERROR');
    }).
    then(
      data =>{
        if(data){
          setUserPosts(data); }
      }
    )
    ;
  }

  function onLogout(){
    setError('');
    setUsername('');
    setLoginStatus(LOGIN_STATUS.NOT_LOGGED_IN);
    setUserPosts([]);

    fetchLogout() 
    .catch( err => {
      setError(err?.error || 'ERROR'); 
    });
  }
   
  function onComment(comment,id){
    setError('');
     fetchUpComment(comment, id)
     .then( data =>{
      setMessage(data.successMessage);
       return fetchCards();}
     ) 
     .catch( err => {
      setError(err?.error || 'ERROR');
    })
    .then(posts=>{
      setData(posts);
   });
  }
  
  function onDeleteCard(id){
    setError('');
    fetchDeleteCard(id).then(
      data =>{
        setMessage(data.successMessage);
        return fetchMyCards();
        //fetch to refresh the mycards data
    }).catch( err => {
      setError(err?.error || 'ERROR');
    }).then(data =>{
      setUserPosts(data);
      return fetchCards();
      //fetch tpo refresh cards data again
    }).then(posts=>{
      
      setData(posts);
   });
  
  }

  function onAddPost(title,address, text,image){
    setError('');
    setIsPagePending(true);
    fetchUpPost(title,address,text,image)
    .then( data =>{
       setMessage(data.successMessage);
           //maybe to add so redirect message
       return fetchCards();
       //fetch tpo refresh cards data again
    }
    )
    .catch( err => {
      setError(err?.error || 'ERROR');
    }).then(posts=>{
      setData(posts);
      //redirect to home page
      setPage('Home');
      setIsPagePending(false);
      return fetchMyCards();
      //fetch to refresh the mycards data
   }).
    then(
      data =>{
        setUserPosts(data); 
      }
    
   );
  }

 
  useEffect(
    () => {
      checkSession();
    },
    [] 
  );
  

  return (
  
      <div className='app'> 
         <header>
          <h1 className='header_title'>Seattle Foodie</h1>
          <MenuBar loginStatus={loginStatus} username={username} setPage={setPage} onLogout={onLogout}/>
           {isPagePending ? <p className='header_message'>Loading... please wait!!</p> : <p className='header_message'>{error}{message}</p>}
         </header>
        {/* { error && <Status error={error}/> } */}
        { page==='Home'&& <Home data={data} loginStatus={loginStatus} onComment={onComment} onDeleteCard={onDeleteCard}/>} 
        { page==='Login'&&<LoginForm onLogin={onLogin}/>}  
        { page==='MyPage'&& <MyPage userPosts={userPosts} onDeleteCard={onDeleteCard}/>}
        { page==='AddPost'&& <AddPostForm loginStatus={loginStatus} username={username} onAddPost={onAddPost} />}           
    </div>
  )
}

export default App;
