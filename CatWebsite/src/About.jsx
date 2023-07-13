import { useState } from 'react';
import './About.css';
import Button from './Button';

const panel = [
    { 
        img: "https://placekitten.com/200/200?image=12",
    alt: "A tabby cat is laying and crossing its paws on the windowsill.",
    title: "What are the different breeds of cats?",
    p:"There are numerous breeds of cats, each with their unique physical and personality traits. Some of the most popular cat breeds include the Persian, Siamese, Maine Coon, Bengal, and Sphynx. Persians are known for their luxurious coats and affectionate personalities, while Siamese cats are talkative and intelligent. Maine Coons are large, gentle giants, while Bengals are energetic and playful. ",
    },

    {
        img:"https://placekitten.com/200/?image=14",
        alt:"A tabby cat is squatting with a mirror on the right which reflects the cat.",
        title:'How do I train my cat?',
        p:"Training a cat requires patience, consistency, and positive reinforcement. Clicker training and reward-based training are effective techniques that can be used to train cats. Training should be started with basic commands, such as come or sit, and gradually progressed to more complex behaviors. It is essential to remember that cats are unique and may respond differently to training methods, so it's important to tailor the training to the individual cat's needs and preferences.",
    },

];

function About() {

    const [isOpen, setIsOpen] = useState({
        0: false,
        1: false
    });

    const onClick = (index) => {
        let newIsOpen = {...isOpen};
        newIsOpen[index] = !isOpen[index];
        setIsOpen(newIsOpen);
 
    };


    const panels = panel.map((item, index) =>{
        return(
            <div className='accordion'key={item.title} >
            <Button 
            onClick={() => onClick(index)}
            aria-expanded= {isOpen? 'true' : 'false'} 
            className={`button-accordion ${isOpen[index]? 'accordion--open' :''}`} 
            ><span>{item.title}</span></Button>
           { isOpen[index]&& (
           <div className='main__p' >
            <img className='p__pic' src={item.img} alt={item.alt}></img>
            <h2 className="p__title"></h2>
            <p className='p__text'>{item.p}</p>
            </div>)}
            </ div>
        )
    }

    );
    return (
       <>
      <h1 className='about__header'>FAQ</h1>
      {panels}
       </>


    );

}
export default About;