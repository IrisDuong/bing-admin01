import {Row,Col} from "antd";
import {Outlet} from "react-router-dom";
const Main = props=>{
    return (
        <>
            <Row>
                <Col span={24}>
                    <Outlet></Outlet>
                </Col>
            </Row>
        </>
    )
}

export default Main;