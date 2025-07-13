import {useState, useEffect, useContext} from "react";
import {useTranslation} from "react-i18next";
import {Row,Col,Table,Button, Input, Select} from "antd";
import {LANG_CODE, CURRENT_LANG_CODE} from "../../../util/config";
import BaseCodeContext from "../base-code/BaseCodeContext";

const {Option} = Select;

const GeneralCodeGridData = props =>{
    const {t} = useTranslation();
    
    const _baseCodeContext = useContext(BaseCodeContext);
    
    const columns = [
        {title:  t("common.dataGrid.no") , dataIndex:"rowNo",key:"rowNo"
        },
        {title: t("module.item.setting.baseCode.generalCode"), dataIndex:"detailCode",key:"detailCode",with:200,
            render:(text,record)=>{
                if(record.addFlg && record.addFlg === "1")   return (
                    <>
                    <div className="bh-form-input-vertical">
                            <Input
                                className="bh-form-input w-120"
                                name="detailCode"
                                onChange={(e)=>onGeneralCodeFormChange(e,record.rowNo)}
                            />
                    </div>
                    </>
                )
                return <span>{text}</span>
            
            }
        },
        {title: t("module.item.setting.baseCode.generalCodeValue"), dataIndex:"codeName",key:"codeName",
        render : (text,record)=>{
            if(record.addFlg && record.addFlg === "1"){
                var localeInputCmp = record.listLocaleInputs.map(v=>{
                    return (

                        <div className="bh-form-input-vertical">
                            <Input
                                className="bh-form-input w-200"
                                name="codeName"
                                onChange={(e)=>onLocaleInputChange(e,record.rowNo,v.langCode)}
                                prefix={buildInputFormPrefix(v.langCode)}
                            />
                        </div>
                    )
                })
                return localeInputCmp;
            }else{
                return <span>{text}</span>
            }
        }
        
        },
        {title:  t("module.item.setting.baseCode.generalCodeUseYN") , dataIndex:"useYn",key:"useYn"
        ,render:(text,record)=>{
            if(record.addFlg && record.addFlg === "1"){
                return (
                    <Select
                        name="useYn"
                        className=" bh-form-select w-120" 
                        // defaultValue={"Y"}
                        value={record.useYn}
                        onChange={(val)=> handleChangeUseYN(val,record.rowNo)}
                    >
                        <Option value="Y">Use</Option>
                        <Option value="N">Not Use</Option>
                    </Select>
                )
            }else{
                return <span>{text}</span>
            }
        }}
    ]

    /**
     * 
     * Build UI
     */
    const buildInputFormPrefix = (value)=>{
        return(
            <>
                <span >{value}</span>
            </>
        )
    }
    

    /**
     * Events
     */
    const onLocaleInputChange = (e,rowNo,langCode)=>{
        var updateGeneralCodes = _baseCodeContext.listGeneralCodes.filter(k=> k.rowNo === rowNo)[0];
        let filterByLangCode = updateGeneralCodes.listLocaleInputs.filter(v=> v.langCode === langCode)[0];
        filterByLangCode.codeName = e.target.value;
    }
    const onGeneralCodeFormChange = (e,rowNo)=>{
        var updateGeneralCodes = _baseCodeContext.listGeneralCodes.filter(k=> k.rowNo === rowNo)[0];
        updateGeneralCodes.detailCode = e.target.value;
       
    }
    const handleChangeUseYN = (e,rowNo) =>{
        console.log("handleChangeUseYN");
        console.log(e);
        let updateGeneralCodes = _baseCodeContext.listGeneralCodes.filter(e=> e.rowNo === rowNo)[0];
        updateGeneralCodes.useYn = e;
    }

    const rowSelection = {
        onChange : (selectedRowKeys, selectedRows) =>{

        }
    }
    return(
        
            <section className="bh-list-data">
                <Table
                    dataSource={_baseCodeContext.listGeneralCodes}
                    columns={columns}
                    rowSelection={{
                        type : "checkbox",
                        ...rowSelection
                    }}
                ></Table>
            </section>
    )
}

export default GeneralCodeGridData;