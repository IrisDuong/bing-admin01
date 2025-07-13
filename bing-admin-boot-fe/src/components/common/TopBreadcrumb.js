import React from "react";
import {Breadcrumb} from "antd";
import { IoHome } from "react-icons/io5";
const TopBreadcrumb = props =>{
    const breadcrumbItems = [
        {
           title : <HomeItem></HomeItem>
        },
        {
           title : "Quản Lý Sản Phẩm"
        }
    ]
    return(
        <>
            <Breadcrumb separator=">" items={breadcrumbItems}></Breadcrumb>
        </>
    )
}

const HomeItem = () =>{
    return(
        <span><IoHome></IoHome> Trang Chủ</span>
    )
}
export default TopBreadcrumb;