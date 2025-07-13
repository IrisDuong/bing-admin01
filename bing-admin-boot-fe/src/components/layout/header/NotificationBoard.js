import React from "react";
import {Space} from "antd";
import { IoIosMail, IoLogoFacebook , IoMdNotificationsOutline  } from "react-icons/io";
import { IoMailOutline } from "react-icons/io5";
import { MdHelpCenter } from "react-icons/md";
import SlackIcon from "../../../public/images/job-notification/slack_icon.png";
import GmailIcon from "../../../public/images/job-notification/gmail_icon.png";
const NotificationBoard = props =>{
    return(
        // <Row className="notification-board">
        //     <Col span={6}>
        //         <MdHelpCenter  className="icon"></MdHelpCenter>
        //     </Col>
        //     <Col span={6}>
        //         <IoMdNotificationsOutline  className="icon"></IoMdNotificationsOutline>
        //     </Col>
        //     <Col span={6}>
        //         <IoMailOutline className="icon"></IoMailOutline>
        //     </Col>
        //     <Col span={6}>
        //         <img className="job-img"  src={SlackIcon}/>
        //     </Col>
        //     {/* <Col span={6}>
        //         <img className="job-img" src={GmailIcon}/>
        //     </Col>
        //     <Col span={6}>
        //         <img className="job-img"  src={SlackIcon}/>
        //     </Col> */}
        // </Row>
        
        <Space className="bh-notification-bar">
            <MdHelpCenter  className="icon"></MdHelpCenter>
            <IoMdNotificationsOutline  className="icon"></IoMdNotificationsOutline>
            <IoMailOutline className="icon"></IoMailOutline>
            {/* <img className="job-img"  src={SlackIcon}/> */}
        </Space>
        
    )
}
export default NotificationBoard;