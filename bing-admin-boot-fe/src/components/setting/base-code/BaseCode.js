import {Link} from "react-router-dom";
import {Row,Col,Tabs} from "antd";
import {useTranslation} from "react-i18next";
import CommonCode from "../common-code/CommonCode";
import {BaseCodeProvider} from "./BaseCodeContext";
import {AppProvider} from "../../../AppContext";
const BaseCode = props=>{
    const {t} = useTranslation();
    
    const items = [
        {
            key: "1",
            label :t("module.tabs.setting.baseCode"),
            children : <CommonCode></CommonCode>,
            className : "bh-md-tabs-item"
        },
    ]
    return (
        <>
        <section className="bh-module">
            <BaseCodeProvider>
                <Tabs
                    defaultActiveKey="1"
                    items={items}
                    // tabBarStyle={{border:"none"}}
                ></Tabs>
            </BaseCodeProvider>
        </section>
        </>
    )
}

export default BaseCode;