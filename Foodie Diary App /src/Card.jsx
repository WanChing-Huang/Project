import './Card.css';
import Dialog from './Dialog';


function Card({ data, loginStatus, onComment, onDeleteCard,className ,inMyPage}) {

   

    const cards = data.map(item=>{
       
        return (
            <div className={className} key={item.id}>
                {inMyPage &&<button type="button" className='delete_button' onClick={(e)=>{ console.log(e.target)
        onDeleteCard(item.id);}}>x</button>}
                <img className='card__pic' src={item.img} alt='food pic' />
                <h2 className='card__title'>{item.title}</h2>
                <p className='card_address'>{item.address}</p>
                <Dialog loginStatus={loginStatus} onComment={onComment} item={item} id={item.id} />
            </div>

        )})
  
    return (
        <div className='main__cards'>
            {cards}

        </div>


    );

}



export default Card;
