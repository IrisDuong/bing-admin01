import {Link} from "react-router-dom";
import {Row,Col,Tabs} from "antd";
import {useTranslation} from "react-i18next";
import TopBreadcrumb from "../../common/TopBreadcrumb";
import ProductList from "./list-product/ProductList";
import CreateProduct from "./create-product/NewProductRegistration";
import ProductStatistic from "./statistic-product/ProductStatistic";
const Product = props=>{
    const {t} = useTranslation();

    const items = [
        {
            key: "1",
            label : t("module.tabs.warehouse.product.list"),
            children : <ProductList></ProductList>,
            className : "bh-md-tabs-item"
        },
        
        {
            key: "2",
            label :t("module.tabs.warehouse.product.statistic"),
            children : <ProductStatistic></ProductStatistic>,
            className : "bh-md-tabs-item"
        },
        
        {
            key: "3",
            label : t("module.tabs.warehouse.product.create"),
            children : <CreateProduct/>,
            className : "bh-md-tabs-item"
        }
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

export default Product;