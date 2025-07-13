import {Row,Col} from "antd";
import LeftMenu from "./LeftMenu";
import Logo from "../header/Logo";
const LeftSideBar = props=>{
    return (
        <>
            <Row>
                <Col span={8}>
                    <Logo></Logo>
                </Col>
                <Col span={16}>
                    {/* <h5>Hệ thống sản phẩm handmade & quà tặng Bing House</h5> */}
                    <h3 className="system-title">BING HOUSE</h3>
                </Col>
            </Row>
            <Row>
                <Col span={24}>
                    <LeftMenu></LeftMenu>
                </Col>
            </Row>
        </>
    )
}

export default LeftSideBar;