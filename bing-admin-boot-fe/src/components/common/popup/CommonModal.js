import {Modal, message} from "antd";
import {ExclamationCircleOutlined } from "@ant-design/icons";
import ModalProps from "./ModalProps";

export const SaveConfirmModal = (handleOK) =>{
    Modal.confirm({
        title : (<ModalProps {...{value:"common.popup.title.saveConfirm"}}></ModalProps>),
        icon : <ExclamationCircleOutlined/>,
        okText : (<ModalProps {...{value:"common.popup.footer.save.okBtn"}}></ModalProps>),
        cancelText : (<ModalProps {...{value:"common.popup.footer.save.cancelBtn"}}></ModalProps>),
        onOk :  handleOK,
        centered : true
    })
}

export const WarningModal = message =>{
    Modal.warning({
        title : message,
        centered : true,
        okText : (<ModalProps {...{value:"common.popup.footer.okBtn"}}></ModalProps>),
        icon : <ExclamationCircleOutlined/>,
    })
}