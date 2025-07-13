import {useState, useEffect, useContext} from "react";
import {useTranslation} from "react-i18next";
import {Row,Col,Table,Button, Input, Select, Space} from "antd";
import { IoMdAdd } from "react-icons/io";
import { FaSave } from "react-icons/fa";
import { RiDeleteBin6Line } from "react-icons/ri";
import DialogWarning from "../../common/popup/DialogWarning";
// import BaseCodeService from "../../../service/baseCode/BaseCodeService";
import {getListGeneralCodeByCommonCode, createGeneralCode} from "../../../service/baseCode/BaseCodeService";
import {LANG_CODE, CURRENT_LANG_CODE} from "../../../util/config";
import {_registrationSuccessNotfication} from "../../common/popup/CommonNotification";
import {SaveConfirmModal} from "../../common/popup/CommonModal";
import BaseCodeContext from "../base-code/BaseCodeContext";
import GeneralCodeGrid from "./GeneralCodeGridData";

const GeneralCode = props=>{
    const {t} = useTranslation();
    const _baseCodeContext = useContext(BaseCodeContext);
    const [isDisabledBtn, setisDisabledBtn] = useState(true)
    const [btnNewDisabledClassName, setBtnNewDisabledClassName] = useState("bh-btn-disaled");
    // const {selectedCommonCode} = useState({commonCode:'',commonName:''});
    const {selectedCommonCode} = _baseCodeContext;


   
    /**
     * Events
     */
    const handleGetListGeneralCodeByCommonCode = async ()=>{
        if(selectedCommonCode != null){
            if(selectedCommonCode.commonCode && selectedCommonCode.commonCode != ''){
                var listGeneralCodes = [];
                let result = await getListGeneralCodeByCommonCode(
                    {
                        commonCode: selectedCommonCode.commonCode,
                        langCode : CURRENT_LANG_CODE
                    }
                )
                // listGeneralCodes = result && result.length > 0 ? result.forEach(e=>e.addFlg = "0") :[]
                if(result && result.length > 0){
                    listGeneralCodes = result.map(e=>{
                        return (
                            {rowNo:e.rowNo,commonCode: e.commonCode,detailCode:e.detailCode,codeName:e.codeName,useYn:e.useYn,addFlg:"0",listLocaleInputs:[]}
                        )
                    })
                }
                _baseCodeContext.setListGeneralCodes(listGeneralCodes)
            }
        }
        console.log("listGeneralCodes",listGeneralCodes);
    }
    const handleAddGeneralCode = val => {
        if(selectedCommonCode  !== null){
            
            let rowNo = _baseCodeContext.listGeneralCodes.length + 1;
            let listLocaleInputs = LANG_CODE.map(e=>  ({langCode : e.langCode, codeName:""}))
            _baseCodeContext.setListGeneralCodes(oldArr => [...oldArr,
                {rowNo:rowNo,commonCode: _baseCodeContext.selectedCommonCode.commonCode,detailCode:"",codeName:"",useYn:"Y",addFlg:"1",listLocaleInputs:listLocaleInputs}]
            );
           
            //reset button
            setisDisabledBtn(false)
            // setBtnNewDisabledClassName("bh-btn-save")
        }else{
            DialogWarning({message : "Please select Common Code First !"})
            setisDisabledBtn(true);
            // setBtnNewDisabledClassName("bh-btn-disaled")
        }
    }


    const saveGeneralCode = ()=>{
        if(_baseCodeContext.listGeneralCodes != null){
            SaveConfirmModal(async ()=>{
                try {

                    await createGeneralCode(_baseCodeContext.listGeneralCodes);
                    _baseCodeContext.setListGeneralCodes([]);
                    _baseCodeContext.setSelectedCommonCode({
                        commonCode : "",
                        commonName : ""
                    });
                    _baseCodeContext.setSelectedCommonCodeRowKeys("");
                    setisDisabledBtn(true);
                    _registrationSuccessNotfication();
                } catch (error) {
                    
                }
            })
        }else{
            DialogWarning({message : "List General Codes is required at least 1."})
        }
        console.log("param for GeneralCode saving = ");
        console.log(_baseCodeContext.listGeneralCodes);
    }
    useEffect(()=>{
        handleGetListGeneralCodeByCommonCode();
        if(_baseCodeContext.selectedCommonCode) setisDisabledBtn(false);
        
    },[_baseCodeContext.selectedCommonCode])
    return (
        <>
        <div style={{marginLeft:"30px"}}>
            
                    <GeneralCodeGrid></GeneralCodeGrid>
                    
                    <section className="bh-btn-group">
                            <Space size={"large"}>
                                
                            <Button
                                    disabled={isDisabledBtn} 
                                    className="bh-btn new"
                                    onClick={()=>handleAddGeneralCode(true)}
                                >
                                    <IoMdAdd className="bh-btn-icon"></IoMdAdd> 
                                        {t("common.button.add")}
                                </Button>
                                <Button
                                    disabled={isDisabledBtn}
                                    className="bh-btn delete"
                                    onClick={()=>{
                                    }}
                                >
                                    <RiDeleteBin6Line className="bh-btn-icon"></RiDeleteBin6Line> 
                                        {t("common.button.del")}
                                </Button>
                                <Button
                                    disabled={isDisabledBtn}
                                    className="bh-btn save"
                                    // className={`bh-btn save ${btnNewDisabledClassName}`}
                                    onClick={saveGeneralCode}
                                >
                                    <FaSave className="bh-btn-icon"></FaSave> 
                                        {t("common.button.save")}
                                </Button>
                            </Space>
                    </section>
                 
        </div>
        </>
    )
}

export default GeneralCode;