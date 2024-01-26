import './MenuBar.css';
import {LOGIN_STATUS} from './constants.js';

function MenuBar({ loginStatus, username, setPage, onLogout }) {

    function openPage (event,page){
        event.preventDefault();                     
        setPage(page);
    }
   
    const loginUser = loginStatus === LOGIN_STATUS.IS_LOGGED_IN ?  <span className='menu-bar__menu-bar-span'>Hello,{username}</span> :  <span className='menu-bar__menu-bar-span'>Please Login....</span>

    return (
   
     
        <nav className="menu">
        <ul className='menu-bar__list'>
        <li className='menu-bar__menu-item'>
          {loginUser}
        </li>
        <li className='menu-bar__menu-item'>
            
         <a className='menu-bar__menu-link' 
             href='/home' 
             onClick={ (e)=> (openPage(e, 'Home'))}
             >Home
         </a>
        </li>
        <li className='menu-bar__menu-item'>
         <a className='menu-bar__menu-link' 
             href='/login' 
             onClick={ (e)=> (openPage(e, 'Login'))}
             >Login
         </a>
        </li>
        { loginStatus === LOGIN_STATUS.IS_LOGGED_IN&&<>
        <li className='menu-bar__menu-item'>
         <a className='menu-bar__menu-link' 
             href='/addPost' 
             onClick={ (e)=> (openPage(e, 'AddPost'))}
             >Add Post
         </a>
        </li>
        <li className='menu-bar__menu-item'>
         <a className='menu-bar__menu-link' 
             href='/myPage' 
             onClick={ (e)=> (openPage(e, 'MyPage'))}
             >My Page
         </a>
        </li></>}
        <li className='menu-bar__menu-item'>
         <a className='menu-bar__menu-link' 
             href='/logout' 
             onClick={ onLogout }
             >Logout
         </a>
        </li>
        </ul>
        </nav>
      

    );

}
export default MenuBar;