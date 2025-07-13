import React,{createContext,useState} from "react";

const AppContext = createContext();
export const AppProvider = ({children}) => {
    const [authenticated,setAuthenticated] = useState(false);
    const [loggedUser,setLoggedUser] = useState(null);
    const [btnSize, setBtnSize] = useState("middle");
    const [currentLang, setCurrentlang] = useState("vi");
    return(
        <AppContext.Provider value={
            {
                authenticated,setAuthenticated,
                loggedUser,setLoggedUser,
                btnSize, setBtnSize,
                currentLang, setCurrentlang
            }}>
            {children}
        </AppContext.Provider>
    )
}

export default AppContext;