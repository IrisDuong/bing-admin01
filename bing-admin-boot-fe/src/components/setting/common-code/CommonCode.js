
import { useEffect, useState, useContext } from "react";
import {Link} from "react-router-dom";
import {useTranslation} from "react-i18next";
import {Row,Col,Button, Drawer, Space} from "antd";
import { IoMdAdd , IoMdRemove} from "react-icons/io";
import { RiDeleteBin6Line } from "react-icons/ri";
import {WarningModal} from "../../../components/common/popup/CommonModal";
import CommonCodeDataGrid from "./CommonCodeDataGrid";
import GeneralCode from "../general-code/GeneralCode";
import CommonCodeSearchForm from "./CommonCodeSearchForm"
import CommonCodeRegistration from "./CommonCodeRegistration";
import BaseCodeContext from "../base-code/BaseCodeContext";
import AppContext from "../../../AppContext";
const CommonCode = props=>{
    const {t} = useTranslation();
    const _baseCodeContext = useContext(BaseCodeContext);
    const _appContext = useContext(AppContext);

    const {btnSize} = _appContext;
    const [commonCodeData, setCommonCodeData] = useState(null);

    /**
     * Events
     */
    const handleDeleteCommonCodes = async () =>{
        console.log("list selected common code to delete >> ", _baseCodeContext.listSelectedCommonCodes);
        if(_baseCodeContext.listSelectedCommonCodes.length === 0){
            WarningModal(
                t("module.popup.setting.baseCode.commonCodeSelectionWarning")
            );
        }
    }
    useEffect(()=>{

    },[])
    return (
        <>
        <div className="bh-module">
            <Row>
                <Col span={8}>
                    
                    <CommonCodeSearchForm></CommonCodeSearchForm>
                </Col>
            </Row>
            <Row>
                <Col span={12}>
                    <CommonCodeDataGrid>
                    </CommonCodeDataGrid>
                    <div className="bh-func-btn-group">
                        <Row>
                            <Col span={5}>
                            </Col>
                            <Col span={5}>
                               
                            </Col>
                        </Row>
                    </div>
                    <section className="bh-btn-group">
                            <Space size={"large"}>
                                
                                <Button
                                    size={btnSize}
                                    className="bh-btn new"
                                    onClick={()=> {
                                        _baseCodeContext.setIsOpenCMCRegPpup(true);
                                        _baseCodeContext.setFlagSearchCommonCode(false);
                                    }}
                                >
                                    <IoMdAdd className="bh-btn-icon"></IoMdAdd> 
                                    {t("common.button.add")}
                                </Button>
                                <Button
                                    size={btnSize}
                                    className="bh-btn delete"
                                    onClick={handleDeleteCommonCodes}
                                >
                                        <RiDeleteBin6Line className="bh-btn-icon"></RiDeleteBin6Line> 
                                    {t("common.button.del")}
                                </Button>
                            </Space>
                    </section>
                    <Drawer
                        width={1000}
                        title={t("module.popup.setting.baseCode.commonCodeCreationTitle")}
                        placement="right"
                        open={_baseCodeContext.isOpenCMCRegPpup}
                        onClose={()=> _baseCodeContext.setIsOpenCMCRegPpup(false)}
                        maskClosable={false}
                    >
                        <CommonCodeRegistration></CommonCodeRegistration>
                    </Drawer>
                </Col>
                <Col span={12}>
                    <GeneralCode 
                        commonCodeData={commonCodeData}
                    >
                    </GeneralCode>
                </Col>
            </Row>
        </div>
        </>
    )
}

export default CommonCode;