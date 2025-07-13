import React from "react";
import {useTranslation} from "react-i18next";
import {Row, Col, Form, Table, Button, Input, Space, Link, Dropdown} from "antd";
import { DownOutlined, Plu} from '@ant-design/icons';
import { CiEdit } from "react-icons/ci";
import { IoIosRemoveCircleOutline,IoIosAddCircle} from "react-icons/io";
const {Column} = Table;
const ProductCategoryList =props =>{
    const {t} = useTranslation();
    
    const categoryActiveItems = {
      items:
      [
        {
          label : "Active"
        },
        {
          label : "Inactive"
        }
      ]
    }
    const data = [
      {
        productCategoryCode : 1,
        productCategoryName: "Hoa",
        productsQty:4000,
        isActive : true,
        children:[
          {
            productCategoryCode : 11,
            productCategoryName: "Hoa Khô",
            productsQty:0,
            isActive : true
          }, {
            productCategoryCode : 12,
            productCategoryName: "Hoa Sáp",
            productsQty:0,
            isActive : true
          }
        ]
      },
      
      {
        productCategoryCode : 2,
        productCategoryName: "Nhồi Bông",
        productsQty:1290,
        isActive : true, children:[
          {
            productCategoryCode : 21,
            productCategoryName: "Thú Nhồi Bông",
            productsQty:0,
            isActive : true
          }, {
            productCategoryCode : 22,
            productCategoryName: "Gối Bông",
            productsQty:0,
            isActive : true
          }
        ]
      },
      
      {
        productCategoryCode : 3,
        productCategoryName: "Đồ Dùng Tiện Ích",
        productsQty:30,
        isActive : false
      }
    ];
    const buildCategoryItem = ({productCategoryCode,productCategoryName,parentCategoryId,treeLevel,orderSeq})=>{
      return(
        <div>
          <div>
            {productCategoryName}
          </div>
          <div style={{float:"right"}}>
            <Button><IoIosAddCircle style={{margin:"1px 5px 0 0"}}></IoIosAddCircle>{t("common.button.add")}</Button>
          </div>
        </div>
      )
    }
    return(
        <>
            <Row>
                <Col span={24}>
                    <div className="bh-search-form">
                    </div>
                    <div className="">
                      <Table
                        bordered="false"
                        dataSource={data}
                      >
                          <Column title="Danh Mục"  key="productCategoryName" width="30%" 
                            render={(_,record)=>{
                              return buildCategoryItem({"productCategoryCode":record.productCategoryCode,"productCategoryName":record.productCategoryName});
                            }}
                          >
                          </Column>
                          <Column title="Tổng Số Lượng Sản Phẩm" dataIndex="productsQty" key="productsQty" width="10%"></Column>
                          <Column 
                            width="20%"
                            title="Tình Trạng"
                            key="status"
                            render={(_,record)=>(
                              <Dropdown
                                menu={categoryActiveItems}
                              > 
                                <Space style={{width:"180px",height:"30px",border:"1px solid #cdcdcd",borderRadius:"6px",padding:"0 10px"}}>
                                  <p style={{width:"8px",height:"8px",borderRadius:"50%",backgroundColor:record.isActive ? "#1ecf1e":"#a39898"}}></p>
                                  <span>{record.isActive ? "Kinh Doanh":"Không Kinh Doanh"}</span>
                                  <DownOutlined style={{float:"right"}} />
                                </Space>
                              </Dropdown>
                             
                            )
                            }
                          ></Column>
                          <Column
                            width="20%"
                            key="action"
                            render={(_,record)=>(
                              <Space style={{fontSize:"15px",fontWeight:400}} size={50}>
                                <span style={{color:"#0c7a9d"}}>
                                  <CiEdit></CiEdit> Chỉnh Sửa
                                </span>
                                <span style={{color:"#0c7a9d"}}>
                                  <IoIosRemoveCircleOutline></IoIosRemoveCircleOutline> Xóa
                                </span>
                              </Space>
                            )}
                          ></Column>
                      </Table>
                    </div>
                </Col>
            </Row>
            
        </>
    )
}

export default ProductCategoryList;