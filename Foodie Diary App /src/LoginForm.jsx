
import { useState } from 'react';


function LoginForm({ onLogin }) {
  
  const [username, setUsername] = useState('');

  function onChange(e) {
    setUsername(e.target.value);
  }

  function onSubmit(e) {
    e.preventDefault(); 
    if(username) {  
      onLogin(username); 
    }
  }

  return (
      <div className="login">
        <h2 className='login_title'></h2>
        <form className="login__form" action="#/login" onSubmit={onSubmit}>
          <label>
            <span>Username:</span>
            <input className="login__username" value={username} onChange={onChange}/>
          </label>
          <button className="login__button" type="submit">Login</button>
        </form>
      </div>
  );

}

export default LoginForm;
