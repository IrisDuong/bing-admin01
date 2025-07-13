import {Link} from "react-router-dom";
import { LuListFilter } from "react-icons/lu";
import { LuUsers, LuUserCircle } from "react-icons/lu";
import {CiDeliveryTruck,CiSettings} from "react-icons/ci"
import {TbBuildingWarehouse} from "react-icons/tb"
import {GoTasklist} from "react-icons/go"
import {Row,Col,Menu} from "antd";
import { useTranslation } from 'react-i18next';
const LeftMenu = props=>{
    const {t} = useTranslation();
    function getItem(label, key, icon, children, type) {
        return {
          key,
          icon,
          children,
          label,
          type,
        };
      }
    
    function navLinkRendering(path,label,icon,type){
        var navLinkClassName = "";
        if(path && type){
            if(path === "" && type === "group"){
                navLinkClassName = "";
            }else if(path === "" && type === "single"){
                navLinkClassName = "nav-link-disable";
            }
        }
        return (
            <>
                
                <div>
                    <span className="bh-nav-icon">
                        {icon}
                    </span>
                    <span className="bh-nav-label">
                        <Link to={path} className={navLinkClassName}>
                                    {label}
                        </Link>
                    </span>
                </div>
            </>
        )
    }
    
    var menuItems = [
        getItem(navLinkRendering("",t("mainMenu.warehouse.name"),<TbBuildingWarehouse/>,"group"),"wareHouseMngt",null
                    ,[
                        getItem(navLinkRendering("/product",t("mainMenu.warehouse.child.product"),"","single"),"product"),
                        getItem(navLinkRendering("/productCategory",t("mainMenu.warehouse.child.productCategory"),"","single"),"productCategory"),
                    ]
                    
        ),
        getItem(navLinkRendering("",t("mainMenu.partner.name"),<LuUsers/>,"group"),"partnerMngt",null
                    ,[
                        getItem(navLinkRendering("/supplier",t("mainMenu.partner.child.supplier"),"","single"),"supplier"),
                        getItem(navLinkRendering("/supplier",t("mainMenu.partner.child.delivery"),"","single"),"logistic"),
                        getItem(navLinkRendering("/supplier",t("mainMenu.partner.child.salesCollaborator"),"","single"),"coll"),
                    ]
                    
        ),
        getItem(navLinkRendering("/hr",t("mainMenu.hr.name"),<LuUserCircle/>,"single"),"hrMngt",null
                    ,[
                        // getItem(navLinkRendering("/user/all","Tất Cả","","single"),"all"),
                        // getItem(navLinkRendering("/user/admin","Admin","","single"),"admin"),
                        // getItem(navLinkRendering("/user/mod","Điều Phối","","single"),"mod"),
                        // getItem(navLinkRendering("/employee","Nhân Viên","","single"),"empMngt"),
                    ]
                    
        ),
        getItem(navLinkRendering("/delivery",t("mainMenu.delivery.name"),<CiDeliveryTruck/>,"single"),"delivery"),
        getItem(navLinkRendering("/task",t("mainMenu.task.name"),<GoTasklist/>,"single"),"task"),
        getItem(navLinkRendering("",t("mainMenu.setting.name"),<CiSettings/>,"group"),"settingMngt",null
                    ,[
                        getItem(navLinkRendering("/baseCode",t("mainMenu.setting.child.baseCode"),"","single"),"baseCode"),
                        getItem(navLinkRendering("/permission",t("mainMenu.setting.child.permission"),"","single"),"permission")
                    ]
                    
        ),
    ]
    
    return (
        <>
        <section className="bh-nav">
            <Row>
                <Col span={24}>
                    <Menu
                        mode="inline"
                        theme="light"
                        items={menuItems}
                        style={{borderInlineEnd:"none"}}
                    ></Menu>
                </Col>
            </Row>
        </section>
        </>
    )
}

export default LeftMenu;