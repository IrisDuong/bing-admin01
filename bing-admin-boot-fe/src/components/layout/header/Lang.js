import React,{useState, useContext} from "react";
import {Space, Dropdown ,Select, Typography,Menu} from "antd";
import USA from "../../../public/images/flag/usa_28.png";
import VN from "../../../public/images/flag/vn_28.png";
import AppContext from "../../../AppContext";
import {useTranslation} from "react-i18next";
const Option = Select.Option;

const Lang = ()=>{
    const {t, i18n} = useTranslation();
    const _appContext = useContext(AppContext);

    const [listLanguages,setListLanguages]= useState([
        {id:"vi",langName:"Vietnames",flag:VN},
        {id:"en",langName:"English",flag:USA}
    ])
    const [lang,setLang] = useState( {id:listLanguages[0].id,langName:listLanguages[0].langName,flag:listLanguages[0].flag},)
    const changeLanguage = lang =>{
        let findLang = listLanguages.filter(e=>e.id === lang)[0]
        setLang({...findLang})
        i18n.changeLanguage(lang)
    }
    const items = ()=>{
        return  listLanguages && listLanguages.map(e=> ({label:(<a href="#" onClick={()=> changeLanguage(e.id)}>{e.langName}</a>)} ));
    }
    return(
        <Space className="language">
            <Dropdown
                menu={{items:items()}}
                // trigger={['click']}
             >
                <Space  size={10} >
                <img src={lang.flag}></img>
                <Typography.Text>{lang.langName}</Typography.Text>
                </Space>
            </Dropdown>
        </Space>
    )
}
export default Lang;