
import {useEffect,useState, useContext} from "react";
import {Link} from "react-router-dom";
import {useTranslation} from "react-i18next";
import {Row,Col,Select,Input,Button, Form} from "antd";
import axios from "../../../util/axios-config";
import {getListCommonCodeByWorkCode} from "../../../service/baseCode/BaseCodeService";
import BaseCodeContext from "../base-code/BaseCodeContext";

const {Option} = Select;

const ProductListSearchForm = props=>{
    const {t} = useTranslation();
    const _baseCodeContext = useContext(BaseCodeContext);

    const [listWorkCodes, setListWorkCodes] = useState([
        {detailCode:"SY",codeName:"System"},
        {detailCode:"MB",codeName:"Member"},
        {detailCode:"WH",codeName:"Warehouse"},
        {detailCode:"BM",codeName:"Business Management"}
    ])
    const [selectedWorkCode, setSelectedWorkCode] = useState(listWorkCodes[0].detailCode);
    /**
     * 
     *  Build UI
     */
    const buildWorkCodeSelection = ()=>{
        var listOpt = listWorkCodes.map((e,index)=><Option key={index} value={e.detailCode} >{e.codeName}</Option>)
        return listOpt;
    }

    /**
     * Events
     */
    
    const handleGetListCommonCodeByWorkCode = async (workCode)=>{
        var _listCommonCodes= await getListCommonCodeByWorkCode(workCode);
        _baseCodeContext.setListCommonCodes(_listCommonCodes ? _listCommonCodes :[])
        _baseCodeContext.setListGeneralCodes([])
     }
    const searchListCommonCode = (workCode)=>{
        setSelectedWorkCode(workCode)
        handleGetListCommonCodeByWorkCode(workCode);
    }
   
    useEffect(()=>{
        if(_baseCodeContext.flagSearchCommonCode){
             handleGetListCommonCodeByWorkCode(selectedWorkCode)
        }
    },[_baseCodeContext.flagSearchCommonCode])
    return (
        <>
            <div className="bh-search-form">
                <Row>
                    <Col span={24}>
                        <Row>
                            <Col span={8}>
                                <Form.Item className="bh-form-label" label={t("module.item.setting.baseCode.workCode")}></Form.Item>
                            </Col>
                            <Col span={16}>
                                <Select 
                                    className="bh-form-select w-200"
                                     defaultValue={selectedWorkCode}
                                     value={selectedWorkCode}
                                     onChange={searchListCommonCode}
                                >
                                    {buildWorkCodeSelection()}
                                  </Select>
                            </Col>
                        </Row>
                    </Col>
                </Row>
            </div>
        </>
    )
}

export default ProductListSearchForm;