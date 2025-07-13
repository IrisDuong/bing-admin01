import React,{useEffect,useState, useContext} from "react";
import {useNavigate,useLocation} from "react-router-dom";
import { Button } from "antd";
import {getLoggedUser} from "../../../service/auth/auth.service";
import {deleteAccessToken,deleteRefreshToken} from "../../../service/auth/token.service";
import Home from "../Home";
import CustomerLoginPage from "../../auth/CustomerLoginPage";
import AppContext from "../../../AppContext";

const ProtectedPage = props =>{
  const navigate = useNavigate();
  const _appContext = useContext(AppContext);
  const loadCurrentLoggedUser = async () =>{
  console.log(">>> loadCurrentLoggedUser ");
    let loggedUser = await getLoggedUser();
    if(loggedUser != null){
      _appContext.setAuthenticated(true);
      _appContext.setLoggedUser(loggedUser);
    }else{
      _appContext.setAuthenticated(false);
      _appContext.setLoggedUser(null);
      deleteAccessToken();
      deleteRefreshToken();
      navigate("/login")
    }
  }

  useEffect(()=>{
      loadCurrentLoggedUser();
  },[])
    return (
      <>
          <Home></Home>
      </>
    )
}

export default ProtectedPage;