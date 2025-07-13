
import {useState, useContext} from "react";
import {useNavigate} from "react-router-dom";
import {Row,Col,Select,Input,Button, Form, Space} from "antd";
import { BsSave } from "react-icons/bs";
import { RxReset } from "react-icons/rx";
import {useTranslation} from "react-i18next";
import {SaveConfirmModal} from "../../../common/popup/CommonModal"
import {createSupplier} from "../../../../service/supplier/supplier.service"
import SupplierContext from "../SupplierContext";
import AppContext from "../../../../AppContext";
const {Option} = Select;
const TextArea = Input.TextArea;
const SupplierRegistration = props=>{
    const {t} = useTranslation();
    const _navigate = useNavigate();
    const _supplierContext = useContext(SupplierContext);
    const _appContext = useContext(AppContext);
    const {btnSize} = _appContext;
    const [supplier, setSupplier] = useState({
        supplierCode : "",
        supplierName : "",
        phoneNo : "",
        address : "",
        email : "",
        fax : "",
        websiteAddr : "",
        statusName : "",
        statusCode : ""
    })
    const onFormChange = e =>{
        let {name, value} = e.target
        setSupplier(prevState=>{
            return{
                ...prevState,
                [name] : value
            }
        })
    }
    
    const handleResetSupplier = ()=>{
       setSupplier(
        {
            supplierCode : "",
            supplierName : "",
            phoneNo : "",
            address : "",
            email : "",
            fax : "",
            websiteAddr : "",
            statusName : "",
            statusCode : ""
        })
    }
    const handleSaveSupplier =  (value)=>{
        console.log(">>>>>>> supplier");
        console.log(supplier);
        SaveConfirmModal(async ()=>{
            let result = await createSupplier(supplier);
            if(result){
                handleResetSupplier();
                _supplierContext.setActiveTabKey("1")
            }
        })
    }
    return (
        <div className="bh-search-form">
                <Form
              
                > 
                    <Row gutter={24}>
                        <Col span={7}>
                            <Form.Item
                                label= {t("module.item.partner.supplier.supplierName")}
                                labelCol={{span:10}}
                                labelAlign="left"
                            >
                                <Input 
                                    onChange={onFormChange}
                                    name="supplierName"
                                    value={supplier.supplierName}
                                    className="bh-form-input w-180"
                                ></Input>
                            </Form.Item>
                        </Col>
                        <Col span={7}>
                            <Form.Item
                                label= {t("common.info.email")}
                                labelCol={{span:10}}
                                labelAlign="left"
                            >
                                <Input
                                    onChange={onFormChange}
                                    name="email"
                                    value={supplier.email}
                                    className="bh-form-input w-180"></Input>
                            </Form.Item>
                        </Col>
                        <Col span={7}>
                            <Form.Item
                                label= {t("module.item.partner.supplier.websiteAddr")}
                                labelCol={{span:10}}
                                labelAlign="left"
                            >
                                <Input
                                    onChange={onFormChange}
                                    name="websiteAddr"
                                    value={supplier.websiteAddr}
                                    className="bh-form-input w-180"></Input>
                            </Form.Item>
                        </Col>
                    </Row>
                    <Row gutter={24}>
                        <Col span={7}>
                            <Form.Item
                                label= {t("common.info.phoneNo")}
                                labelCol={{span:10}}
                                labelAlign="left"
                            >  
                                <Input 
                                    onChange={onFormChange}
                                    name="phoneNo"
                                    value={supplier.phoneNo}
                                    className="bh-form-input w-180"></Input>
                            </Form.Item>
                        </Col>
                        <Col span={7}>
                            <Form.Item
                                label= {t("module.item.partner.supplier.fax")}
                                labelCol={{span:10}}
                                labelAlign="left"
                            >  
                                <Input
                                    onChange={onFormChange}
                                    name="fax"
                                    value={supplier.fax}
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
                               <TextArea
                                    onChange={onFormChange}
                                    showCount
                                    maxLength={200}
                                    value={supplier.address}
                                    name="address"
                                    // onChange={onChange}
                                    placeholder="disable resize"
                                    style={{
                                        height: 50,
                                        width: 600,
                                        resize: 'vertical',
                                    }}
                                />
                            </Form.Item>
                        </Col>
                    </Row>
                </Form>
                <section className="bh-btn-group">
                    <Space size={"large"}>
                            <Button
                                size={btnSize}
                                icon={<BsSave className="bh-btn-icon"></BsSave>}
                                className="bh-btn save"
                                onClick={handleSaveSupplier}
                            >
                                {t("common.button.save")}
                            </Button>
                            <Button
                                size={btnSize}
                                icon={<RxReset className="bh-btn-icon"></RxReset>}
                                className="bh-btn reset"
                                onClick={handleResetSupplier}
                            >
                                   {t("common.button.reset")}
                            </Button>
                    </Space>
                </section>
            </div>
    )
}

export default SupplierRegistration;