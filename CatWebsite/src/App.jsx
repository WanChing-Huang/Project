import './App.css';
import Header from './Header';
import Main from './Main';
import Footer from './Footer';
import SkipToContent from './SkipToContent';
import { useState } from 'react';


function App() {

  const [ page, setPage] = useState('Home');
  return (
    <div className='app'>
       <SkipToContent className='skip-to-content'/>
       <Header setPage={setPage}/>
       <Main page={page} setPage={setPage} />
       <Footer className='footer'/>
    </div>
  );
}

export default App;
