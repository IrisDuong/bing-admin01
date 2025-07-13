import React,{useEffect, useContext} from "react";
import {useNavigate, useLocation} from "react-router-dom";
import { Modal, Form, Input, Checkbox, Button, message, Row, Col, Divider, notification, Image, Space, Typography} from "antd";
import {LoginOutlined, UserAddOutlined, KeyOutlined } from '@ant-design/icons';
import {GOOGLE_AUTH_URL} from "../../util/config"
import BingLogo from "../../public/images/binglogo1.png"
import GPlus from "../../public/images/gplus_icon.png"
import {login} from "../../service/auth/auth.service";
import AppContext from "../../AppContext";

const CustomerLoginPage = props =>{
    const location = useLocation();
    const navigate = useNavigate();
    const _appContext = useContext(AppContext);
    /**
     * Setting Modal
     */
    const [api, contextHolder] = notification.useNotification();
    const openNotificationWithIcon = (type) => {
      api[type]({
        message: "Login Failed",
        description:
        "You have logged failed. Please try again."
      });
    };

    /**
     * Events
     */
    const onFinish = async  values =>{
        const {userName, password} = values;
        if((userName != '' && password != '' )){
            let loggedUser = await login({userName:userName,password:password});
            if(loggedUser != null){
                _appContext.setAuthenticated(true);
                _appContext.setLoggedUser(loggedUser);
            }else{
                _appContext.setAuthenticated(false);
                _appContext.setLoggedUser(null);
            }
            navigate("/")
        }else{
            _appContext.setAuthenticated(false);
            _appContext.setLoggedUser(null);
            openNotificationWithIcon('User Name & Passwordare are required !')
        }
    }
    const onFinishFailed = errorInfo =>{
        _appContext.setAuthenticated(false);
        _appContext.setLoggedUser(null);
        openNotificationWithIcon('Login process failed !')
    }

    const unauthorizedError = () =>{
        Modal.error({
            title : "Login Process",
            content : "Failed ! Your session is expired. Please login again.",
            centered:true
            
        })
    }

    useEffect(()=>{
        if(location.state != null){
            if(location.state.unauthorizedError && location.state.unauthorizedError === '401'){
                unauthorizedError()
            }
        }
    },[location])
    return(
        <>
            {contextHolder}
            <div className="login-container">
                
                <div className="title">
                    <div>
                    <img src={BingLogo}></img>
                    </div>
                    <div>
                        <h3>Bing House Shop</h3>
                    </div>
                </div>
                <div>
                    <Form
                        name="basic"
                        // labelCol={{span: 3}}
                        wrapperCol={{span: 20}}
                        style={{width: 400, position:"relative", top: 20, left:30}}
                        initialValues={{remember: true}}
                        onFinish={onFinish}
                        onFinishFailed={onFinishFailed}
                        autoComplete="off"
                    >
                        <Form.Item
                            name="userName"
                            rules={[
                                {
                                    required : true,
                                    message: "Please input your user name !"
                                }
                            ]}
                        >

                            <Input prefix={<UserAddOutlined></UserAddOutlined>} placeholder="Username"/>
                        </Form.Item>
                        <Form.Item
                            name="password"
                            rules={[
                                {
                                    required:true,
                                    message : "Please input your password"
                                }
                            ]}
                        >
                            <Input prefix={<KeyOutlined></KeyOutlined>} type="password"  placeholder="Password"/>    
                        </Form.Item>
                        <Form.Item
                        >
                                <Button
                                    className="bh-btn login"
                                    htmlType="submit"
                                    // icon={<FaPowerOff></FaPowerOff>}
                                    size={"large"}
                                    icon={<LoginOutlined></LoginOutlined>}
                                >
                                    Login
                                </Button>
                        </Form.Item>
                        <Form.Item
                            wrapperCol={{
                                offset: 0,
                                span: 7
                            }}
                        >
                            <Checkbox>Remember me</Checkbox>
                        </Form.Item> 
                        <Divider orientation="left">Or</Divider>
                        <Form.Item
                            >
                                    <a href={GOOGLE_AUTH_URL}>Login by Google</a>
                            </Form.Item>
                    </Form>
                    
                </div>
            </div>
        </>
    )
}
export default CustomerLoginPage;