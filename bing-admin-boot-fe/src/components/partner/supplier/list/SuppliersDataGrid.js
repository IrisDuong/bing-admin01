import React,{useContext, useEffect, useState} from "react";
import {Link} from "react-router-dom";
import {Row,Col,Table} from "antd";
import {useTranslation} from "react-i18next";
import SupplierContext from "../SupplierContext";
const SuppliersDataGrid = props=>{
    const {t} = useTranslation();

   const _supplierContext = useContext(SupplierContext);
    const columns = [
            { title: t("common.dataGrid.no") ,dataIndex:"rowNo", key:"rowNo"},
            { title: t("module.item.partner.supplier.supplierCode") ,dataIndex:"supplierCode", key:"supplierCode"},
            { title: t("module.item.partner.supplier.supplierName") ,dataIndex:"supplierName", key:"supplierName"},
            { title: t("common.info.phoneNo"),dataIndex:"phoneNo", key:"phoneNo"},
            { title: t("common.info.address"),dataIndex:"address", key:"address",width: '25%'},
            { title: t("common.info.email"),dataIndex:"email", key:"email"},
    ]
    useEffect(()=>{
        console.log("SuppliersDataGrid");
    },[])
    return (
        <> 
            <Row>
                <Col span={24}>
                    <section className="bh-list-data">
                        <Table
                            columns={columns}
                            dataSource={_supplierContext.listSuppliers}
                        ></Table>
                    </section>
                </Col>
            </Row>
        </>
    )
}

export default SuppliersDataGrid;