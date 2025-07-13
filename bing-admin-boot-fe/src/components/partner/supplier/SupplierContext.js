import React,{useState,createContext} from "react";

const SupplierContext = createContext();

export const SupplierContextProvdier = ({children}) =>{
    const [listSuppliers, setListSuppliers] = useState([]);
    const [selectedSupplier, setSelectedSupplier] = useState(null);
    const [activeTabKey, setActiveTabKey] = useState("1");
    const [flagSearch, setflagSearch] = useState(true);

    return(
        <SupplierContext.Provider
            value={{
                listSuppliers, setListSuppliers,
                selectedSupplier, setSelectedSupplier,
                activeTabKey, setActiveTabKey,
                flagSearch, setflagSearch
            }}
        >
            {children}
        </SupplierContext.Provider>
    )
}

export default SupplierContext;