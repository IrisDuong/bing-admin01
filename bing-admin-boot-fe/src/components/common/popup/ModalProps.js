import React from "react";
import {useTranslation} from "react-i18next";

const ModalProps = props =>{
    const {t} = useTranslation();

    return (
        <>{t(props.value)}</>
    )
}
export default ModalProps;