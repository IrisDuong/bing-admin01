
import { useEffect, useState, useContext } from "react";
import {Link} from "react-router-dom";
import {Row,Col,Table} from "antd";
import {useTranslation} from "react-i18next";
import BaseCodeContext from "../base-code/BaseCodeContext";
const CommonCodeDataGrid = props=>{
    const {t} = useTranslation();
    const _baseCodeContext = useContext(BaseCodeContext);
    const columns = [
        {title: t("common.dataGrid.no") , dataIndex:"rowNo",key:"rowNo",with:10},
        {title: t("module.item.setting.baseCode.commonCode") , dataIndex:"commonCode",key:"commonCode"},
        {title: t("module.item.setting.baseCode.commonCodeName"), dataIndex:"codeName",key:"codeName",with:550},
        {title: t("module.item.setting.baseCode.commonCodeType"), dataIndex:"codeType",key:"codeType"},
        {title: t("module.item.setting.baseCode.commonCodeUseYN"), dataIndex:"useYn",key:"useYn"}
    ]
    const data = _baseCodeContext.listCommonCodes.length > 0 ? _baseCodeContext.listCommonCodes.map(element => {
        var newElement = {...element};
        newElement.key = newElement.commonCode
        return newElement
    }):[];

    /**
     * Events
     */
    const onClickedRow = (param)=>{
        _baseCodeContext.setSelectedCommonCodeRowKeys(param.commonCode);
        _baseCodeContext.setSelectedCommonCode({
            commonCode : param.commonCode,
            commonName : param.codeName
        })
    }

    const rowClassName = (record,index)=>{
      if(record.commonCode ===  _baseCodeContext.selectedCommonCodeRowKeys) return "selected";
      return "";
    }

    const rowSelection = {
        onChange : (selectedRowKeys, selectedRows)=>{
            console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
            _baseCodeContext.setListSelectedCommonCodes([...selectedRowKeys])
        }
    }
    return (
        <>
            <Row>
                <Col span={24}>
                    <section className="bh-list-data">
                       <Table
                        onRow={(record, rowIndex)=>{
                            return{
                                onClick : (event)=>{
                                    onClickedRow({commonCode : record.commonCode, codeName : record.codeName})
                                }
                            }
                        }}
                        dataSource={data}
                        columns={columns}
                        rowClassName={rowClassName}
                        rowSelection={{
                            type : "checkbox",
                            ...rowSelection
                        }}
                        // scroll={{x:400,y:600}}
                        // title={()=>"Common Code"}
                       ></Table>
                    </section>
                </Col>
            </Row>
        </>
    )
}

export default CommonCodeDataGrid;