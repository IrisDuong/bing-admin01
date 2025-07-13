
import {Link} from "react-router-dom";
import {Row,Col,Tabs} from "antd";
import ProductListDataGrid from "./ProductListDataGrid";
import ProductListSearchForm from "./ProductListSearchForm";
const ProductList = props=>{
   
    return (
        <>
            <Row>
                <Col span={24}>
                    <ProductListSearchForm/>
                </Col>
            </Row>
            <Row>
                <Col span={24}>
                    <ProductListDataGrid/>
                </Col>
            </Row>
        </>
    )
}

export default ProductList;