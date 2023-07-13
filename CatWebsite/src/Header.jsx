import './Header.css';
import MenuBar from './MenuBar';

import { useState } from 'react';

function Header({ setPage }) {
    const [ isOpen, setIsOpen ] = useState(false); 
    const dropDown = isOpen? 'dropdown-menu--open' : '' ;

    return (
        <header className='header'>

      
        <h1 className='header__title'>Lovely Cutie Cats</h1>
        <button className={`menu__button ${dropDown}`}
                onClick={()=> setIsOpen(!isOpen)}
                aria-expanded= {isOpen? 'true' : 'false'} 
                aria-haspopup='menu'
                >&#9886; Menu &#9887;</button>
        { isOpen && ( 
        <MenuBar  className='header_nav' setPage={setPage}/> 
        )}
        </header>

    );

}
export default Header;