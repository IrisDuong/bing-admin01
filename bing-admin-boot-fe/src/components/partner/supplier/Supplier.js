import React,{useContext, useState} from "react";
import {useTranslation} from "react-i18next";
import {Link} from "react-router-dom";
import {Row,Col,Tabs} from "antd";
import TopBreadcrumb from "../../common/TopBreadcrumb";
import SupplierList from "./list/SupplierList";
import SupplierRegistration from "./create/SupplierRegistration";
import SupplierContext,{SupplierContextProvdier} from "./SupplierContext";
import { t } from "i18next";
const Supplier = props=>{
    const {t} = useTranslation();

    return (
        <>
           <section className="bh-module">
                <TopBreadcrumb></TopBreadcrumb>
                <SupplierContextProvdier>
                   <SupplierTabs></SupplierTabs>
                </SupplierContextProvdier>
            </section>
        </>
    )
}

const SupplierTabs = ()=>{
    
    const _supplierContext = useContext(SupplierContext);
   
    const items = [
        {
            key: "1",
            label :t("module.tabs.partner.supplier"),
            children : <SupplierList tabKey={"1"}></SupplierList>,
            className : "bh-md-tabs-item"
        },
        
        {
            key: "2",
            label :"Tạo Nhà Cung Cấp",
            children: <SupplierRegistration tabKey={"2"}></SupplierRegistration>,
            className : "bh-md-tabs-item"
        }
    ]

    const onTabChange = key =>{
        console.log(`key tab = ${key}`);
        _supplierContext.setActiveTabKey(key)
    }
    return(
        <Tabs
            onChange={onTabChange}
            animated={{ inkBar: true, tabPane: false}}
            activeKey={_supplierContext.activeTabKey}
            items={items}
        // tabBarStyle={{border:"none"}}
        ></Tabs>
    )
}
export default Supplier;