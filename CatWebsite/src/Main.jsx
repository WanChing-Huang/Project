import './Main.css';
import Home from './Home';
import About from './About';
import News from './News';

function Main({ page , setPage }) {

    return (
        <main className='main' id='main'>
          {(page === 'Home') && <Home/>}
          {(page === 'FAQ') && <About/>}
          {(page === 'News') && <News setPage={setPage}/>}
        </main>

    );

}
export default Main;