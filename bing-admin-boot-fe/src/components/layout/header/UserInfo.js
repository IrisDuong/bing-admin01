import React,{useState, useContext} from "react";
import {useNavigate, Link} from "react-router-dom";
import {Typography, Space, Dropdown} from "antd";
import { DownOutlined, DragOutlined } from '@ant-design/icons';
import { LuPower } from "react-icons/lu";
import { IoLogOutOutline } from "react-icons/io5";
import { FaUserCircle } from "react-icons/fa";
import {logout} from "../../../service/auth/auth.service";
import AppContext from "../../../AppContext";

const UserInfo = props =>{
    const navigate = useNavigate();
    const _appContext = useContext(AppContext);
    const {loggedUser} = _appContext;
    const items = [
        {
            label : (<a href="#">Account Information</a>)
            // label : (loggedUser && loggedUser.fullName ? loggedUser.fullName: "No Name")
        },
        {
            label : (<a href="#">Change Password</a>)
            // label : (loggedUser && loggedUser.fullName ? loggedUser.fullName: "No Name")
        },
        {
            type : "divider"
        },
        {
            label:(
                <Space>
                    <IoLogOutOutline></IoLogOutOutline>
                    <a href="#" onClick={(e)=> handleLogout(e)}>Logout</a>
                </Space>
            )
        }
    ]
    /**
     * Events
     */
    const handleLogout = async (e) =>{
        e.preventDefault();
        let result = await logout();
        if(result) navigate("/login")
    }
    return(
        <>
            <Space className="bh-auth-user-info">
                <Dropdown
                    menu={{
                    items,
                    }}
                >
                    <a onClick={(e) => e.preventDefault()}>
                    <Space>
                        {loggedUser && loggedUser.fullName ? loggedUser.fullName: "No Name"}
                        <DownOutlined />
                    </Space>
                    </a>
                </Dropdown>
            </Space>
            
        </>
    )
}
 export default UserInfo;