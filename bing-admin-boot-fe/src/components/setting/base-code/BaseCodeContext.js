import React,{createContext,useState} from "react";

const BaseCodeContext = createContext();
export const BaseCodeProvider = ({children}) => {
    const [selectedCommonCode,setSelectedCommonCode] = useState(null);
    const [listCommonCodes,setListCommonCodes] = useState([]);
    const [selectedCommonCodeRowKeys,setSelectedCommonCodeRowKeys] = useState("")
    const [listSelectedCommonCodes,setListSelectedCommonCodes] = useState([]);
    const [isOpenCMCRegPpup,setIsOpenCMCRegPpup] = useState(false);
    const [flagSearchCommonCode,setFlagSearchCommonCode] = useState(true);
    const [listGeneralCodes,setListGeneralCodes] = useState([]);
    return(
        <BaseCodeContext.Provider 
            value={{
                selectedCommonCode,setSelectedCommonCode,
                listCommonCodes,setListCommonCodes,
                isOpenCMCRegPpup, setIsOpenCMCRegPpup,
                flagSearchCommonCode,setFlagSearchCommonCode,
                listGeneralCodes, setListGeneralCodes,
                selectedCommonCodeRowKeys,setSelectedCommonCodeRowKeys,
                listSelectedCommonCodes,setListSelectedCommonCodes
            }}>
            {children}
        </BaseCodeContext.Provider>
    )
}

export default BaseCodeContext;