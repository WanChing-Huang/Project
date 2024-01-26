import { useState } from 'react';
import {LOGIN_STATUS} from './constants.js';

import './AddPostForm.css'



function AddPostForm({loginStatus, onAddPost}) {
    
    const [title, setTitle] = useState("");
    const [address, setAddress] = useState("");
    const [text,setText] = useState("");
    const [image, setImage] = useState(null);

    const handleImageChange = (e) => {
        const file = e.target.files[0];
        setImage(file);
      };
    
      
    const onSubmit = (e) =>{
        e.preventDefault(); 
        if(title && text && image ){
           onAddPost(title,address,text,image);
        }
    }

    return (
        <>
        { loginStatus === LOGIN_STATUS.IS_LOGGED_IN ? (
          <form className="post_form" onSubmit={onSubmit}  method='PATCH'>
            <h1>Add your YUMMY dairy HERE!!</h1>
              <label className='post_title' >
                <span>Title :  </span>
                <input className='post_title-input' onChange={(e)=>{setTitle(e.target.value)}}/></label>
              <label className='post_img'>
                <span className='post_img-span'>Picture :   </span>
              <input type="file"  name="avatar" accept=".jpg, .jpeg" onChange={handleImageChange}/>
              </label>
              <label className='post_address'>
                <span className='post_address-span'>Address : </span>
                <input className='post_address-input' onChange={(e) => setAddress(e.target.value)}/></label>

                <textarea className="comment_textarea" rows="10" cols="30" onChange={(e) => {setText(e.target.value)}} placeholder= 'Share any YUMMY mind here!!'>
            </textarea>
            <button className="post__button" type="submit">Add Post</button>
          </form>
        ) : <p className='post_form-message'>Login to add Posts!!</p>}
        </>
    );
}


export default AddPostForm;