
import React,{useContext, useEffect} from "react";
import {Link} from "react-router-dom";
import {Row,Col,Tabs} from "antd";
import {useTranslation} from "react-i18next";
import SupplierSearchForm from "./SupplierSearchForm";
import SuppliersDataGrid from "./SuppliersDataGrid";
import SupplierContext from "../SupplierContext";
const SupplierList = props=>{
    const {t} = useTranslation();

    const _supplierContext = useContext(SupplierContext);

    
    return (
        <>
            <Row>
                <Col span={24}>
                    <SupplierSearchForm parentTabKey={props.tabKey}/>
                </Col>
            </Row>
            <Row>
                <Col span={24}>
                    <SuppliersDataGrid/>
                </Col>
            </Row>
        </>
    )
}

export default SupplierList;