import React,{useEffect} from "react";
import { Modal, Form, Input, Checkbox, Button, message, Row, Col, Divider } from "antd";
import { FaGithub , FaGoogle, FaFacebook} from "react-icons/fa";
import {useNavigate} from "react-router-dom";
import {GOOGLE_AUTH_URL} from "../../util/config"
const LoginPage1 = props =>{
    const navigate = useNavigate();
    const {isOpen, handleOpenLoginPage} = props;

    /**
     * Events
     */
    const onFinish = values =>{
        console.log("success : ", values);
        // login(values)
        // .then(res=>{
        //     navigate("/")
        // })
    }
    const onFinishFailed = errorInfo =>{
        console.log("Failed : ",errorInfo);
    }
    return(
            <Modal
                className="login-wrapper"
                centered
                title="Login to Bing House"
                open={isOpen}
                footer={null}
                closable={true}
                width={600}
                onCancel={()=> handleOpenLoginPage(false)}
            >
                <Row>
                    <Col span={19}>
                        <Form
                            name="basic"
                            labelCol={{span: 8}}
                            wrapperCol={{span: 16}}
                            style={{maxWidth: 900, position:"relative", top: 20}}
                            initialValues={{remember: true}}
                            onFinish={onFinish}
                            onFinishFailed={onFinishFailed}
                            autoComplete="off"
                        >
                            <Form.Item
                                label="Username"
                                name="userName"
                                rules={[
                                    {
                                        required : true,
                                        message: "Please input your user name !"
                                    }
                                ]}
                            >

                                <Input/>
                            </Form.Item>
                            <Form.Item
                                label="Password"
                                name="password"
                                rules={[
                                    {
                                        required:true,
                                        message : "Please input your password"
                                    }
                                ]}
                            >
                                <Input/>    
                            </Form.Item>
                            <Form.Item
                                wrapperCol={{
                                    offset: 12,
                                    span: 12
                                }}
                            >
                                <Button className="btn btn-login"  htmlType="submit">Login</Button>
                                </Form.Item>  
                        </Form>
                    </Col>
                    <Divider>Or Social Login With </Divider>
                    <div className="social-login">
                        <div>
                            <div className="social-item">
                                <a className="btn-social" href={GOOGLE_AUTH_URL}>
                                    <FaGoogle style={{color:""}} className="icon-social"></FaGoogle>
                                    <span>Google</span>
                                </a>
                            </div> 
                            <div className="social-item">
                                    <a className="btn-social">
                                        <FaFacebook className="icon-social"></FaFacebook>
                                        <span>Facebook</span>
                                    </a>
                            </div>
                            <div className="social-item">
                                <a className="btn-social">
                                <FaGithub className="icon-social"></FaGithub> 
                                    <span>Github</span>
                                </a>
                            </div>
                        </div>
                    </div>
                </Row>
            </Modal>
    )
}

export default LoginPage1;