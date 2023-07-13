
import './App.css';
import API from '.axiosConfig';
import React,{ useState, useEffect } from 'react';
import Layout from './api/components/layout';
import {Route, Routes, route} from 'react-router-dom';
import Home from './api/components/home/Home';

function App() {

  const [movies, setMovies] = useState();

  const getMovies = async () => {
    try {

      const response = await API.get("/api/v1/movies");

      console.log(response.data);
      setMovies(response.data);

    } catch (err) {

      console.log(err);
      //log exception on console window
    }
  }
 
  const getMovieData = async (movieId) => {
     
    try 
    {
        const response = await api.get(`/api/v1/movies/${movieId}`);

        const singleMovie = response.data;

        setMovie(singleMovie);

        setReviews(singleMovie.reviews);
        

    } 
    catch (error) 
    {
      console.error(error);
    }

  }


  useEffect(() => {
    getMovies();
  }, []);

  return(
    <div className="App">
    <Header/>
    <Routes>
        <Route path="/" element={<Layout/>}>
          <Route path="/" element={<Home movies={movies} />} ></Route>

          <Route path="/Trailer/:ytTrailerId" element={<Trailer/>}></Route>
          <Route path="/Reviews/:movieId" element ={<Reviews getMovieData = {getMovieData} movie={movie} reviews ={reviews} setReviews = {setReviews} />}></Route>
      
        </Route>
    </Routes>
  </div>);
    
}

export default App;
