
import {useEffect,useState, useContext} from "react";
import {Link} from "react-router-dom";
import {useTranslation} from "react-i18next";
import {Row,Col,Select,Input,Button, Form, Space} from "antd";
import { RxReset } from "react-icons/rx";
import { BsSearch } from "react-icons/bs";
import {searchSuppliers} from "../../../../service/supplier/supplier.service"
import SupplierContext from "../SupplierContext";
import AppContext from "../../../../AppContext";
const {Option} = Select;
const SupplierSearchForm = props=>{
    const {t} = useTranslation();
    const [searchFormData, setSearchFormData] = useState({
        supplierCode : "",
        supplierName : "",
        phoneNo : "",
        address : "",
        email : ""
    })
   
    const _supplierContext = useContext(SupplierContext);
    const _appContext = useContext(AppContext);
    const {btnSize} = _appContext;
    /**
     * Events handling
     */
    
    const onFormChange = e =>{
        let {name, value} = e.target
        setSearchFormData(prevState=>{
            return{
                ...prevState,
                [name] : value
            }
        })
    }
    const handleResetSupplier = ()=>{
        setSearchFormData(
        {
            supplierCode : "",
            supplierName : "",
            phoneNo : "",
            address : "",
            email : ""
        })
    }
    const handleSearchSuppliers = async ()=>{
        console.log("tim ds supplier");
           var listSuppliers = await searchSuppliers(searchFormData);
        if(listSuppliers != null){
            _supplierContext.setListSuppliers(listSuppliers)
        }
    }

    useEffect(()=>{
        if(props.parentTabKey === _supplierContext.activeTabKey) handleSearchSuppliers()
    },[_supplierContext.activeTabKey])
    return (
        <>
            <div className="bh-search-form">
                <Form
              
                > 
                    <Row gutter={24}>
                        <Col span={7}>
                            <Form.Item
                                label= {t("module.item.partner.supplier.supplierCode")}
                                labelCol={{span:10}}
                                labelAlign="left"
                            >
                                <Input
                                    value={searchFormData.supplierCode}
                                    onChange={onFormChange}
                                    name="supplierCode"
                                    className="bh-form-input w-180"></Input>
                            </Form.Item>
                        </Col>
                        <Col span={7}>
                            <Form.Item
                                label= {t("module.item.partner.supplier.supplierName")}
                                labelCol={{span:10}}
                                labelAlign="left"
                            >
                                <Input
                                    value={searchFormData.supplierName}
                                    onChange={onFormChange}
                                    name="supplierName"
                                    className="bh-form-input w-180"></Input>
                            </Form.Item>
                        </Col>
                        <Col span={7}>
                            <Form.Item
                                label= {t("common.info.phoneNo")}
                                labelCol={{span:10}}
                                labelAlign="left"
                            >  
                                <Input
                                    value={searchFormData.phoneNo}
                                    onChange={onFormChange}
                                    name="phoneNo"
                                    className="bh-form-input w-180"></Input>
                            </Form.Item>
                        </Col>
                    </Row>
                    <Row gutter={24}>
                        <Col span={7}>
                            <Form.Item
                                label= {t("common.info.address")}
                                labelCol={{span:10}}
                                labelAlign="left"
                            >
                                <Input
                                    value={searchFormData.address}
                                    onChange={onFormChange}
                                    name="address"
                                    className="bh-form-input w-180"></Input>
                            </Form.Item>
                        </Col>
                        <Col span={7}>
                            <Form.Item
                                label= {t("common.info.email")}
                                labelCol={{span:10}}
                                labelAlign="left"
                            >
                                <Input
                                    value={searchFormData.email}
                                    onChange={onFormChange}
                                    name="email"
                                    className="bh-form-input w-180"></Input>
                            </Form.Item>
                        </Col>
                    </Row>
                </Form>
                <section className="bh-btn-group">
                    <Space size={"large"}>
                            <Button size={btnSize} icon={<BsSearch className="bh-btn-icon"></BsSearch> } className="bh-btn search"
                                onClick={handleSearchSuppliers}
                            >
                                {t("common.button.search")}
                            </Button>
                            <Button size={btnSize} icon={<RxReset  className="bh-btn-icon"></RxReset> } className="bh-btn reset"
                                onClick={handleResetSupplier}
                            >
                                   {t("common.button.reset")}
                            </Button>
                    </Space>
                </section>
            </div>
        </>
    )
}

export default SupplierSearchForm;