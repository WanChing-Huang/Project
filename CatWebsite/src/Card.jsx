import './Card.css';
import Dialog from './Dialog';

const card = [
    {
        img: "https://placekitten.com/200/200?image=3",
        alt: "A tabby cat walk in the snow!",
        title: "Snow walks in the snow!",
        text: "Snow, her paws making soft crunching sounds in the snow. Her piercing blue eyes shone in the moonlight as she searched for her next adventure.The town's people admired her beauty and grace from afar, ",
        buttonText: "Register for more Snowy",
    },

    {
        img: "https://placekitten.com/200/200?image=2",
        alt: "Four kittens stand in a cat bed and the right one is black the other is brown.",
        title: "Cute four kittens !",
        text: "Four tiny kittens snuggled together in a cozy cat bed, their soft purrs filling the room. As they slept peacefully, their mother watched over them with a loving and protective eye..",
        buttonText: "Register for kitten news",

    },

    {
        img: "https://placekitten.com/200/200?image=7",
        alt: "A kitten with brown and orange fur and blue eyes",
        title: "Cathy a new born !",
        text: "A tiny kitten with brown and orange fur bravely explored her new surroundings, her curious eyes taking in the world around her.Discovered new wonders and joys in her new home.",
        buttonText: "Register for Cathy",

    }

]


function Card({setPage}) {
    
    function openPage (event,page){
        setPage(page);
    }
    const cards = card.map(item => {
        return (
            <div className='main__card' key={item.title}>
                <img className='card__pic' src={item.img} alt={item.alt} />
                <h2 className='card__title'>{item.title}</h2>
                <p className='card__text'>{item.text}</p>      
                <Dialog buttonText={item.buttonText}/>
            </div>

        )
    }

    );
    return (
        <div className='main__cards'>
       {cards}
     
        </div>
 
 
     );
    
}



export default Card;


