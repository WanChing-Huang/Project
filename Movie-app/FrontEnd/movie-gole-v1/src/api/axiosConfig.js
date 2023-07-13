import axios, { AxiosRequestConfig, AxiosPromise, AxiosResponse } from 'axios';

console.log(axios);


export default axios.create({
    baseURL:'http://localhost:8080/'
   
});
  