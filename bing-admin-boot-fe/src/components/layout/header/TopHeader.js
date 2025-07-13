import React from "react";
import {Row, Col} from "antd";
import UserInfo from "./UserInfo";
import NotificationBoard from "./NotificationBoard";
import Lang from "./Lang";
const TopHeader = props=>{
    return (
        <section>
                <Row justify={"end"}>
                    <Col span={4} className="bh-head-group">
                        <NotificationBoard/>
                    </Col>
                    <Col span={4} className="bh-head-group">
                        <UserInfo/>
                    </Col>
                    <Col span={3} className="bh-head-group">
                        <Lang/>
                    </Col>
                </Row>
        </section>
    )
}

export default TopHeader;