
import {notification} from "antd";
const placement = "top";

const _notification = ({type,title, desc})=> notification[type]({
    message : title,
    description : desc,
    placement,
    duration : 5
});
export const _saveSuccessNotfication = ()=> _notification({type:"success",title:"Saving",desc:"Saved Successfully !"});
export const _registrationSuccessNotfication = ()=> _notification({type:"success",title:"Registration",desc:"Regisered Successfully !"});