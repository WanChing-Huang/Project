import { useState } from "react";
import Button from "./Button";
import './Carousel.css';
import  catOnPlant from "./pic/catAndPlant.jpeg";
import  catDownStair from "./pic/catDownStair.jpeg";
import  catOnSofa from "./pic/catOnSofa.jpeg";


const slide = [
  {  
    number: 0,
    img: catOnPlant,
    alt: "A grey cat climbs on a plant",
  },

  {  
    number: 1,
    img: catDownStair,
    alt: "A kitten climbs down the woody stair",
  },

  {  
    number: 2,
    img: catOnSofa,
    alt: "A orange cat lies on white sofa",
  },

]

function Carousel({className}){
   const [index, setIndex] = useState(0);
   const [isLeft, setIsLeft]= useState(false);
   const [isRight, setIsRight]= useState(false);
   const length = 3;

   const handleLeft =()=>{
     let newIndex = {...index};
     newIndex =  index - 1;
     setIndex(newIndex < 0 ? length-1 : newIndex);
     let newIsRight = {...isRight};
    newIsRight = false;
    setIsRight(newIsRight);
   }


   const handleRight =()=>{
    let newIndex = {...index};
     newIndex =  index + 1;
    setIndex(newIndex  >= length ? 0 : newIndex);
    let newIsRight = {...isRight};
    newIsRight = true;
    setIsRight(newIsRight);
  }

  const slides = slide.map( item=>{
    return(
    
    (index === item.number) && <img className={`slide__pic ${isRight}`} key={item.alt} src={item.img} alt={item.alt} />
      )
  })


    return(
        <div className={`${className} carousel`} aria-label="carousel">
            {slides}
            <Button className={'button--handle-left'} 
            type="button"
            onClick={handleLeft}   
            >&#8678;</Button>
             <Button className={'button--handle-right'} 
            type="button"
            onClick={handleRight}
            >&#8680;</Button>
            

        </div>
  
    );
    
    }
export default Carousel;