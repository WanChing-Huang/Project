import './Button.css';

function Button ({
    children,
   className,
    onClick,
    type='button', 
    visual='button',
   disable = false,

}){ 
    let buttonClass = 'button'
    if (visual === 'link'){
        buttonClass = 'button-link'
    }

return(
    <button className=
    {`${className} ${buttonClass}`} onClick={onClick}
      type={type} 
      disabled = {disable}
        >
        {children}</button>
);
}
export default Button;