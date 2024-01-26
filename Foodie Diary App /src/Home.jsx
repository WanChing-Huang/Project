import Card from "./Card";
import './Home.css';

function Home({ data, loginStatus, onComment }){
    

    return (
        <>
        <h1 className="home_h1">Enjoy Everyone&#39;s YUMMY Sharing!!</h1>
       <Card className={"home_card"} data={data} loginStatus={loginStatus} onComment={onComment}/>
    </>)



}

export default Home;