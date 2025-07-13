
import {Link} from "react-router-dom";
import {Row,Col,Table} from "antd";
import {useTranslation} from "react-i18next";

const ProductListDataGrid = props=>{
    const {t} = useTranslation();

   const cols = [
        { title: t("common.dataGrid.no") ,dataIndex:"rowNo", key:"rowNo"},
        { title: t("module.item.warehouse.product.productCode") ,dataIndex:"productCode", key:"productCode"},
        { title: t("module.item.warehouse.product.productName") ,dataIndex:"productName", key:"productName"},
        { title: t("module.item.warehouse.productCategory.productCategoryName") ,dataIndex:"productCategory", key:"productCategory"},
        { title: t("module.item.warehouse.productGroup.productGroupName") ,dataIndex:"productGroup", key:"productGroup"},
        { title: t("module.item.warehouse.product.price") ,dataIndex:"productPrice", key:"productPrice"},
        { title: t("module.item.warehouse.product.inStocksQty") ,dataIndex:"inStocksQty", key:"inStocksQty"}
   ]
   const dataSource = [
        {rowNo:1,productCode:"BH-PD-01",productName:"Tranh Cát Hoàng Thành Thăng Long 1945",productGroup:"Tranh Cát",productCategory:"Tranh",productPrice:"580.000",inStocksQty:100},
        {rowNo:2,productCode:"BH-PD-02",productName:"Tranh Cát Đồng Quê Miền Nam",productGroup:"Tranh Cát",productCategory:"Tranh",productPrice:"264.000",inStocksQty:180},
        {rowNo:3,productCode:"BH-PD-03",productName:"Tranh Thêu Công Cha Nghĩa Mẹ",productGroup:"Tranh Thêu",productCategory:"Tranh",productPrice:"800.000",inStocksQty:56},
        {rowNo:4,productCode:"BH-PD-04",productName:"Tranh Thêu Sapa",productGroup:"Tranh Thêu",productCategory:"Tranh",productPrice:"1.500.000",inStocksQty:134},
        {rowNo:5,productCode:"BH-PD-05",productName:"Tranh Thêu Mùa Thu Seoul",productGroup:"Tranh Thêu",productCategory:"Tranh",productPrice:"2.000.000",inStocksQty:250},
        {rowNo:6,productCode:"BH-PD-06",productName:"Hoa Hồng Sáp Số 1",productGroup:"Hoa Sáp",productCategory:"Hoa",productPrice:"150.000",inStocksQty:89},
        {rowNo:7,productCode:"BH-PD-07",productName:"Hoa Hồng Sáp Số 2",productGroup:"Hoa Sáp",productCategory:"Hoa",productPrice:"165.000",inStocksQty:78},
        {rowNo:8,productCode:"BH-PD-08",productName:"Hoa Cúc Sáp Số 1",productGroup:"Hoa Sáp",productCategory:"Hoa",productPrice:"180.000",inStocksQty:45},
        {rowNo:9,productCode:"BH-PD-09",productName:"Chó Bông Billy",productGroup:"Chó Nhồi Bông",productCategory:"Thú Nhồi Bông",productPrice:"360.000",inStocksQty:30},
        {rowNo:10,productCode:"BH-PD-10",productName:"Bò Sữa Lười",productGroup:"Bò Nhồi Bông",productCategory:"Thú Nhồi Bông",productPrice:"450.000",inStocksQty:200},
   ]
    return (
        <>
            <Row>
                <Col span={24}>
                    <section className="bh-list-data">
                        <Table
                            columns={cols}
                            dataSource={dataSource}
                        ></Table>
                    </section>
                </Col>
            </Row>
        </>
    )
}

export default ProductListDataGrid;