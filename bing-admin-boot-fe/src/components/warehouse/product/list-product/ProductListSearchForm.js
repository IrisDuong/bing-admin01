
import {useEffect,useState} from "react";
import {Link} from "react-router-dom";
import {useTranslation} from "react-i18next";
import {Row,Col,Select,Input,Button, Form, Space} from "antd";
import { FaSearch } from "react-icons/fa";
import { MdOutlineClear  } from "react-icons/md";
import axios from "../../../../util/axios-config";
    const {Option} = Select;

const ProductListSearchForm = props=>{
    const {t} = useTranslation();
    const [allProductCategories,setAllProductCategories] = useState([]);
    const [allProductGroups,setAllProductGroups] = useState([]);

    const [searchFormData,setSearchFormData] = useState({
        productCategoryCode : '',
        productGroupCode : ''
    })

    // lay phan loai san pham
    const getAllProductCategories = async () =>{
        var _allProductCategories = [];
        if(_allProductCategories && _allProductCategories.length > 0){
            setSearchFormData(prevState=>{
                return{
                    ...prevState,
                    productCategoryCode : _allProductCategories[0].productCategoryCode
                }
            });

            if(_allProductCategories[0].listProductGroupsDto && _allProductCategories[0].listProductGroupsDto.length > 0){
                setSearchFormData(prevState=>{
                    return{
                        ...prevState,
                        productGroupCode : _allProductCategories[0].listProductGroupsDto[0].productGroupCode
                    }
                })
                setAllProductGroups(_allProductCategories[0].listProductGroupsDto)
            }
        }
        setAllProductCategories(_allProductCategories);
    }

    

    // lay nhom san pham theo phan loai
    const getAllProductGroupsByCategory = async (productCategoryCode)=>{
        setSearchFormData(prevState=>{
            return{
                ...prevState,
                productCategoryCode : productCategoryCode
            }
        })
        var _allProductGroups = [];
        if(_allProductGroups && _allProductGroups.length > 0){
            setSearchFormData(prevState=>{
                return{
                    ...prevState,
                    productGroupCode : _allProductGroups[0].productGroupCode
                }
            })
            setAllProductGroups(_allProductGroups);
        }
    }

    const selectProductGroup = productGroupCode =>{
        setSearchFormData(prevState=>{
            return{
                ...prevState,
                productGroupCode : productGroupCode
            }
        })
    }
    /**
     * 
     *  Build UI
     */
    const buildProductCategoryCmp = ()=>{
        var listProductCategoriesOpt = [];
        if(allProductCategories && allProductCategories.length > 0){
            listProductCategoriesOpt =  allProductCategories.map((e,index)=><Option key={index}  value={e.productCategoryCode}>{e.productCategoryName}</Option>)
        }
        return listProductCategoriesOpt;
    }
    
    const buildProductGroupCmp = ()=>{
        var listProductGroupsOpt = [];
        if(allProductGroups && allProductGroups.length > 0){
            listProductGroupsOpt =  allProductGroups.map((e,index)=><Option key={index}  value={e.productGroupCode}>{e.productGroupName}</Option>)
        }
        return listProductGroupsOpt;
    }
  
    const handleSearchProduct = ()=>{

    }
    useEffect(()=>{
        // getAllProductCategories();
    },[])

    return (
        <>
            <div className="bh-search-form">
                <Row gutter={24}>
                    <Col span={7}>
                        <Form.Item
                            name=""
                            label={t("module.item.warehouse.product.productCode")}
                            labelCol={{span:10}}
                            labelAlign="left"
                        >
                            <Input name="productCode" className="bh-form-input w-180"></Input>
                        </Form.Item>
                    </Col>
                    <Col span={7}>
                        <Form.Item
                            name=""
                            label={t("module.item.warehouse.product.productName")}
                            labelCol={{span:10}}
                            labelAlign="left"
                        >
                            <Input name="productName" className="bh-form-input w-180"></Input>
                        </Form.Item>
                    </Col>
                    <Col span={7}>
                        <Form.Item
                            name=""
                            label={t("module.item.warehouse.productCategory.productCategoryName")}
                            labelCol={{span:10}}
                            labelAlign="left"
                        >  
                            <Select 
                                defaultValue={searchFormData.productCategoryCode} 
                                className="bh-form-select w-180"
                                value={searchFormData.productCategoryCode}
                                onChange={getAllProductGroupsByCategory}
                                name="productCategoryCode"
                            >
                                {buildProductCategoryCmp()}
                            </Select>
                        </Form.Item>
                    </Col>
                </Row>
                <Row gutter={24}>
                    <Col span={7}>
                        <Form.Item
                            name=""
                            label={t("module.item.warehouse.productGroup.productGroupName")}
                            labelCol={{span:10}}
                            labelAlign="left"
                        >
                            <Select 
                                defaultValue={searchFormData.productGroupCode} 
                                className="bh-form-select w-180"
                                value={searchFormData.productGroupCode}
                                onChange={selectProductGroup}
                                name="productGroupCode"
                            >
                                {buildProductGroupCmp()}
                            </Select>
                        </Form.Item>
                    </Col>
                    <Col span={7}>
                        <Form.Item
                            name=""
                            label={t("module.item.partner.supplier.supplierName")}
                            labelCol={{span:10}}
                            labelAlign="left"
                        >
                            <Select 
                                defaultValue={"01"} 
                                className="bh-form-select w-180" 
                                name="productProdvider">
                                    <Option  value="01">An Thịnh Phát</Option>
                                    <Option  value="02">CM Gift</Option>
                                    <Option  value="03">Cơ Sở An Nhiên</Option>
                            </Select>
                        </Form.Item>
                    </Col>
                    <Col span={7}>
                        <Form.Item
                            name=""
                            label={t("module.item.warehouse.product.price")}
                            labelCol={{span:10}}
                            labelAlign="left"
                        >
                            <Input name="productPrice" className="bh-form-input w-180"></Input>
                        </Form.Item>
                    </Col>
                </Row>
                <Row gutter={24}>
                    <Col span={7}>
                        <Form.Item
                            name=""
                            label={t("module.item.warehouse.product.productStatus")}
                            labelCol={{span:10}}
                            labelAlign="left"
                        >  
                            <Select 
                                defaultValue={"01"} 
                                className="bh-form-select w-180" 
                                name="productStt">
                                    <Option  value="01">Tốt</Option>
                                    <Option  value="02">Hư Hỏng</Option>
                                    <Option  value="03">Cần Chỉnh Sửa</Option>
                                    <Option  value="04">Đã Xóa</Option>
                            </Select>
                        </Form.Item>
                    </Col>
                    <Col span={7}>
                        <Form.Item
                            name=""
                            label={t("module.item.warehouse.product.importStatus")}
                            labelCol={{span:10}}
                            labelAlign="left"
                        >
                            <Select 
                                defaultValue={"01"} 
                                className="bh-form-select w-180" 
                                name="productImportStt">
                                    <Option  value="01">Mới</Option>
                                    <Option  value="02">Chờ Nhập</Option>
                                    <Option  value="03">Đã Nhập Kho</Option>
                                    <Option  value="04">Đã Xuất Kho</Option>
                                    <Option  value="05">Thanh Lý</Option>
                            </Select>
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
        </>
    )
}

export default ProductListSearchForm;