import React from "react";
import {useTranslation} from "react-i18next";
import {Row, Col, Form, Table, Button, Input, Space, Select} from "antd";
import { FaSearch } from "react-icons/fa";
import { MdOutlineClear  } from "react-icons/md";

const {Option} = Select;
const ProductGroupList =props =>{
    const {t} = useTranslation();

    
   const cols = [
    { title: t("common.dataGrid.no"),dataIndex:"rowNo", key:"rowNo"},
    { title: t("module.item.warehouse.productGroup.productGroupCode"),dataIndex:"productGroupCode", key:"productCode"},
    { title: t("module.item.warehouse.productGroup.productGroupName"),dataIndex:"productGroupName", key:"productName"},
]
const dataSource = [
    {rowNo:1,productGroupCode:"BHPG-01",productGroupName:"Tranh Cát"},
    {rowNo:2,productGroupCode:"BHPG-02",productGroupName:"Tranh Sáp"},
    {rowNo:3,productGroupCode:"BHPG-03",productGroupName:"Gấu Bông"},
    {rowNo:4,productGroupCode:"BHPG-04",productGroupName:"Heo Bông"},
    {rowNo:5,productGroupCode:"BHPG-05",productGroupName:"Phụ Kiện Thời Trang"},
    {rowNo:5,productGroupCode:"BHPG-06",productGroupName:"Đồ Chơi Bé Trai"},
    {rowNo:5,productGroupCode:"BHPG-07",productGroupName:"Hoa Sáp"},
    {rowNo:5,productGroupCode:"BHPG-08",productGroupName:"Hoa Giấy"},
]
    return(
        <>
            <div className="bh-search-form">
                <Row gutter={8}>
                    <Col span={7}>
                        <Form.Item
                            name=""
                            label={t("module.item.warehouse.productCategory.productCategoryName")}
                            labelCol={{span:10}}
                            labelAlign="left"
                        >
                            <Select 
                                defaultValue={"BHPG-01"} 
                                className="bh-form-select w-180"
                                name="productTypeCode"
                            >
                                <Option key="BHPG-01" value="BHPG-01">Tranh</Option>
                                <Option key="BHPG-02" value="BHPG-02">Thú Bông</Option>
                                <Option key="BHPG-03" value="BHPG-03">Phụ Kiện</Option>
                                <Option key="BHPG-04" value="BHPG-04">Heo Bông</Option>
                                <Option key="BHPG-05" value="BHPG-05">Đồ Chơi</Option>
                                <Option key="BHPG-06" value="BHPG-06">Hoa</Option>
                            </Select>
                        </Form.Item>
                    </Col>
                    <Col span={7}>
                        <Form.Item
                            name=""
                            label={t("module.item.warehouse.productGroup.productGroupName")}
                            labelCol={{span:10}}
                            labelAlign="left"
                        >
                            
                            <Select 
                                defaultValue={"BHPG-01"} 
                                className="bh-form-select w-180"
                                name="productGroupCode"
                            >
                                <Option key="BHPG-01" value="BHPG-01">Tranh Cát</Option>
                                <Option key="BHPG-02" value="BHPG-02">Tranh Thêu</Option>
                                <Option key="BHPG-03" value="BHPG-03">Tranh Sơn Dầu</Option>
                                <Option key="BHPG-04" value="BHPG-04">Tranh Sáp</Option>
                            </Select>
                        </Form.Item>
                    </Col>
                    <Col span={7}>
                        <Form.Item
                            name=""
                            label={t("module.item.warehouse.productGroup.productGroupCode")}
                            labelCol={{span:10}}
                            labelAlign="left"
                        >
                            <Input name="productGroupCode" className="bh-form-input w-180"></Input>
                        </Form.Item>
                    </Col>
                </Row>
                <section className="bh-btn-group">
                    <Space size={"large"}>
                            <Button className="bh-btn search"
                                >
                                    <FaSearch className="bh-btn-icon"></FaSearch> 
                                    {t("common.button.search")}
                            </Button>
                            <Button  className="bh-btn reset"
                                >
                                    <MdOutlineClear  className="bh-btn-icon"></MdOutlineClear> 
                                    {t("common.button.reset")}
                            </Button>
                    </Space>
                </section>
            </div>
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

export default ProductGroupList;