import "./Dialog.css";
import Button from './Button.jsx'
import { useRef } from "react";
import { useId } from 'react';
import { useState } from "react";

function Dialog({buttonText}) {
    const dialogRef = useRef();
    const id = useId();
    const [name, setName]= useState("");
    const [confirmEmail, setConfirmEmail] = useState("");
    const [email, setEmail]= useState("");
    const [isInvalid, setIsInvalid] = useState(false);
    const [isEmailInvalid, setIsEmailInvalid] = useState(false);
    const [isShow, setIsShow] = useState(false);

    


    return (
        <>
            <Button visual="link" onClick={() => dialogRef.current.showModal()}>{buttonText}</Button>
            <dialog className="dialog" ref={dialogRef}>
                <header className="title">Register for more Cat news!!!</header>

                <form className="form" 
                     onSubmit={(e)=>{
                     e.preventDefault(); 
                     setIsInvalid(!name);
                     setIsEmailInvalid(!email || !confirmEmail|| !email.search('@') || !confirmEmail.search('@') || email !== confirmEmail);
                     setIsShow(!isShow);
                     }} >
                    { isInvalid && (<p className="name__error-message">Name must has value!</p>)}
                    { isEmailInvalid && (<p className="email__error-message">Email must has @ and match confirm email!</p>)}
                    { isShow&&!isInvalid && !isEmailInvalid && (<p className="success-message">Successfully submitted please close the window!</p>)}
                 
                    
                    <label className="form__name " htmlFor={`${id}-name`}>Name: {name}
                    </label>
                    <input id={`${id}-name`} className="input__name"   
                    onChange={(e)=>setName(e.target.value)}
                    value={name}
                    />

                    <label className="form__email"  htmlFor={`${id}-email`}>Email: {email}
                    </label>
                    <input id={`${id}-email`} className="input__email" onChange={(e)=>setEmail(e.target.value)} value={email}/>


                    <label className="form__confirm-email"  htmlFor={`${id}-confirm-email`}>Confirm Email: {confirmEmail}
                    </label>
                    <input id={`${id}-confirm-email`} className="input__confirm-email" onChange={(e)=>setConfirmEmail(e.target.value)} value={confirmEmail}/>


                    <Button className={"form-close"} 
                    onClick={() => {
                        dialogRef.current.close()

                        let newName = {...name};
                        newName = "";
                        !isInvalid &&setName(newName)

                        let newEmail = {...email};
                        newEmail = "" ;
                        !isEmailInvalid&& setEmail(newEmail);

                        let newConfirmEmail = {...confirmEmail};
                        newConfirmEmail = "" ;
                        !isEmailInvalid&& setConfirmEmail(newConfirmEmail);

                        setIsShow(false);

                        ;}}
                    >close</Button>
                    <Button className={"form-submit"} type="submit" visual="link">Submit</Button>

                </form>


            </dialog>
        </>
    )



}

export default Dialog;