import React,{useState,useEffect, useContext} from "react";
import {useTranslation} from "react-i18next";
import {Row,Col,Select,Input,Button, Form, Space, Typography} from "antd";
import { MdOutlineClear  } from "react-icons/md";
import { FaSave } from "react-icons/fa";
import { TiDelete } from "react-icons/ti";
import {LANG_CODE} from "../../../util/config"
// import BaseCodeService from "../../../service/baseCode/BaseCodeService";
import {getListCommonCodeByWorkCode,createCommonCode} from "../../../service/baseCode/BaseCodeService";
import AppContext from "../../../AppContext";
import BaseCodeContext from "../base-code/BaseCodeContext";
import {_registrationSuccessNotfication} from "../../common/popup/CommonNotification";
import {SaveConfirmModal} from "../../common/popup/CommonModal";
const {Text} = Typography;
const {Option} = Select;

const CommonCodeRegistration = props =>{
    const {t} = useTranslation();
    const _appContext = useContext(AppContext);
    const _baseCodeContext = useContext(BaseCodeContext);
    const {btnSize} = _appContext;
    
    const [formData, setFormData] = useState({
        commonCode : "",
        codeType:"SG",
        useYn:"",
        workCode:"",
        listLocaleInputs:LANG_CODE.map(e=>  ({langCode : e.langCode, codeName:"", langName : e.langName}))
    });
    const [listWorkCodes, setListWorkCodes] = useState([
        {detailCode:"SY",codeName:"System"},
        {detailCode:"MB",codeName:"Member"},
        {detailCode:"WH",codeName:"Warehouse"},
        {detailCode:"BM",codeName:"Business Management"}
    ]);
    const [listUseYns, setlistUseYns] = useState([
        {detailCode:"Y",codeName:"Use"},
        {detailCode:"N",codeName:"Not Use"}
    ]);
    /**
     * 
     *  Build UI
     */
    const buildWorkCodeSelection = ()=>{
        var listOpt = listWorkCodes.map((e,index)=><Option key={index} value={e.detailCode} >{e.codeName}</Option>)
        return listOpt;
    }

    
    const buildUseSelection = ()=>{
        var listOpt = listUseYns.map((e,index)=><Option key={index} value={e.detailCode} >{e.codeName}</Option>)
        return listOpt;
    }
    const buildLangCodeName = ()=>{
        var listLangCodeNames = formData.listLocaleInputs.map((e,index)=>{
            return (
                <div className="bh-form-wrapper-multi-row">
                    <Row gutter={4}>
                        <Col className="gutter-row" span={3}>
                            {/* <Input key={e.langCode} className="bh-form-input w-80" disabled name="langCode" value={e.langName}/> */}
                            <span className="text-item highlights">
                                <Text>
                                    {e.langName}
                                </Text>
                            </span>
                        </Col>
                        <Col className="gutter-row" span={5}>
                            <Input key={e.langCode}  className="bh-form-input w-200" value={e.codeName}   name="codeName"  onChange={(v)=>changeLangCodeName(e.langCode,v)}/>
                        </Col>
                    </Row>
                </div>
            )
        })
        return listLangCodeNames;
    }

    /**
     * 
     *  Events
     */
    const onSelectionChange = (name, value) =>{
        updateFormData(name, value)
    }
    const changeLangCodeName = (langCode,e)=>{
        let _listLocaleInputs =  [...formData.listLocaleInputs];
        _listLocaleInputs.map(k=> {
            if(k.langCode == langCode) k.codeName = e.target.value;
        })
        // let filteredLocal = formData.listLocaleInputs.filter(e=> e.langCode == langCode)[0];
        // let result = {...filteredLocal};
        setFormData(prevState=>({...prevState,listLocaleInputs:_listLocaleInputs}))
        var h = 0;
    }
    const updateFormData = (propName, value)=>{
        setFormData(prevState=>{
            return{
                ...prevState,
                [propName]:value
            }
        })
    }
    const saveCommonCode = () =>{
        var param = {...formData};
        // param.listLocaleInputs = listLocaleInputs;
        SaveConfirmModal(async ()=>{

            try {
                await createCommonCode(param);
                _registrationSuccessNotfication();
                _baseCodeContext.setIsOpenCMCRegPpup(false);
                _baseCodeContext.setFlagSearchCommonCode(true);
                resetForm();
            } catch (error) {
                
            }
        })
    }

    const resetForm = () =>{
        let _listCommonCodes = LANG_CODE.map(e=>  ({langCode : e.langCode, codeName:"", langName : e.langName}))
        setFormData(
            {
                commonCode : "",
                codeType:"SG",
                useYn:listUseYns[0].detailCode,
                workCode:listWorkCodes[0].detailCode,
                listLocaleInputs:_listCommonCodes
            }
        );
    }
    useEffect(()=>{
        if(listUseYns && listUseYns.length > 0) updateFormData("useYn",listUseYns[0].detailCode)
        if(listWorkCodes && listWorkCodes.length > 0) updateFormData("workCode",listWorkCodes[0].detailCode)
    },[])
    return(
        <>
            <div>
                <div>
                    <Row>
                        <Col span={4}>
                            <Form.Item className="bh-form-label" label={t("module.item.setting.baseCode.workCode")}></Form.Item>
                        </Col>
                        <Col span={20}>
                            <Select
                                defaultValue={listWorkCodes[0].detailCode}
                                value={formData.workCode}
                                className="bh-form-select w-285"
                                onChange={(val)=> onSelectionChange("workCode",val)}
                            >
                                {buildWorkCodeSelection()}
                            </Select>
                        </Col>
                    </Row>
                </div>
                <div>
                    <Row>
                        <Col span={4}>
                            <Form.Item className="bh-form-label"label={t("module.item.setting.baseCode.commonCodeName")}></Form.Item>
                        </Col>
                        <Col span={20}>
                            {buildLangCodeName()}
                        </Col>
                    </Row>
                </div> 
                <div>
                    <Row>
                        <Col span={4}>
                            <Form.Item className="bh-form-label"label={t("module.item.setting.baseCode.commonCodeType")}></Form.Item>
                        </Col>
                        <Col span={20}>
                            <Input disabled className="bh-form-input w-80"   name="codeName" value={"Single"}/>
                        </Col>
                    </Row>
                </div>
                <div>
                    <Row>
                        <Col span={4}>
                            <Form.Item className="bh-form-label"label={t("module.item.setting.baseCode.commonCodeUseYN")}></Form.Item>
                        </Col>
                        <Col span={20}>
                            <Select 
                                defaultValue={listUseYns[0].detailCode}
                                value={formData.useYn}
                                className="bh-form-select w-285"
                                onChange={(val)=>onSelectionChange("useYn",val)}
                            >
                               {buildUseSelection()}
                            </Select>
                        </Col>
                    </Row>
                </div>
                
                <section className="bh-btn-group">
                    <Space size={"large"}>
                            
                        <Button
                            className="bh-btn create"
                            size={btnSize}
                            icon={<FaSave  className="bh-btn-icon"></FaSave> } 
                            onClick={saveCommonCode}
                        >
                           {t("common.button.save")}
                        </Button>
                        <Button 
                            className="bh-btn reset"
                            size={btnSize}
                            icon={<MdOutlineClear  className="bh-btn-icon"></MdOutlineClear> }
                            onClick={resetForm}
                        >
                            {t("common.button.reset")}
                        </Button>
                    </Space>
                </section>
            </div>
        </>
    )
}
 export default CommonCodeRegistration