import React,{useEffect, useContext} from 'react';
import logo from './logo.svg';
import './App.css';
import {RouterProvider,createBrowserRouter,Navigate, useLocation} from "react-router-dom";
import {routes} from "./routes";
import Home from './components/layout/Home';
import ProtectedRouter from './components/layout/protected/ProtectedPage';
import CustomerLoginPage from './components/auth/CustomerLoginPage';
import OAuth2Redirect from './components/auth/OAuth2Redirect';
import AppContext from "./AppContext";
function App() {
  /** state */
 
  const _appContext = useContext(AppContext);

  const router = createBrowserRouter([
      {
      path : "/",
      element : <ProtectedRouter></ProtectedRouter>,
      children: routes,
      },
      {
        path:"/login",
        element:<CustomerLoginPage></CustomerLoginPage>
      },
      {
        path:"/oauth2/redirect",
        element:<OAuth2Redirect></OAuth2Redirect>
      }
    ]
  );
  useEffect(()=>{
    document.title = "Bing House"
  },[])
  return (
    <div className="App">
          <RouterProvider router={router}></RouterProvider>
    </div>
  );
}
export default App;
