import React,{useEffect,useState, useContext} from "react";
import {Navigate} from "react-router-dom";
import {Row,Col} from "antd";
import { AxiosInterceptor } from '../../util/axios-config';
import TopHeader from "./header/TopHeader";
import LeftSideBar from "./sidebar/LeftSideBar";
import RightSideBar from "./sidebar/RightSideBar";
import AppContext from "../../AppContext";
const Home = props=>{
    const _appContext = useContext(AppContext);
    return (
        <>
        <AxiosInterceptor>
         <Row>
                <Col span={4} className="sidebar left">
                    <LeftSideBar/>
                </Col>
                <Col span={20} className="sidebar"> 
                    <header>
                        <TopHeader/>
                    </header>
                    <RightSideBar/>
                </Col>
            </Row>
        {
            // !_appContext.authenticated && <Navigate to="/login" replace></Navigate>
        }
               
        </AxiosInterceptor>
        </>
    )
}

export default Home;