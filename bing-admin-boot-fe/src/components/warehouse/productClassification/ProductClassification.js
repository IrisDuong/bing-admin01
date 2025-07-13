import React,{useEffect, useState} from 'react';
import {Link} from "react-router-dom";
import {useTranslation} from "react-i18next";
import {Row,Col,Tabs} from "antd";
import TopBreadcrumb from "../../common/TopBreadcrumb";
import ProductGroup from './productGroup/ProductGroup';
import ProductCategory from './productCategory/ProductCategory';
const ProductClassification = props=>{

    const {t} = useTranslation();

    const items = [
        {
            key: "1",
            label : t("module.tabs.warehouse.productClassification.productCategory"),
            className : "bh-md-tabs-item",
            children: <ProductCategory></ProductCategory>
        },
        
        {
            key: "2",
            label : t("module.tabs.warehouse.productClassification.productGroup"),
            className : "bh-md-tabs-item",
            children :<ProductGroup></ProductGroup>
        },
    ]
    return (
        <>
            <section className="bh-module">
                <TopBreadcrumb></TopBreadcrumb>
                <Tabs
                    animated={{ inkBar: true, tabPane: false}}
                    defaultActiveKey="1"
                    items={items}
                    // tabBarStyle={{border:"none"}}
                ></Tabs>
            </section>
        </>
    )
}

export default ProductClassification;