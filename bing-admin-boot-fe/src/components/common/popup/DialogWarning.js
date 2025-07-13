
import {Modal} from "antd"
const DialogWarning = param =>{
    const {message} = param;
    return  Modal.confirm({
                centered:true,
                title : "Warning",
                content : message,
                footer: (_, { OkBtn, CancelBtn }) => (
                    <>
                      <OkBtn />
                    </>
                  ),
            })
       
}

export default DialogWarning;