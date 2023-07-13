import './MenuBar.css';
const menu = [
        {
            name: "Home",
            path: "/",
        },
        {
            name: "FAQ",
            path: "/",
        },
        {
            name: "News",
            path: "/",
        },

    ];

function MenuBar({ className, setPage  }) {

    function openPage (event,page){
        event.preventDefault();                     
        setPage(page);
    }
   
    const list = menu.map ( item => {
        return(
        <li className='menu-bar__menu-item' key={item.name}>
         <a className='menu-bar__menu-link' 
             href='item.path' 
             onClick={ (e)=> (openPage(e, item.name))}
             >{item.name}
         </a>
        </li>
        );
    });


    return (
     
        <nav className={`menu ${className}`}>
        <ul className='menu-bar__list'>
            {list}
        </ul>
        </nav>
      

    );

}
export default MenuBar;