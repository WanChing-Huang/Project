import { Outlet } from "react-route-dom";

import React from 'react';

const Layout = () =>{
    return(
        <main>
         <Outlet></Outlet>
        </main>
    )
}

export default Layout;