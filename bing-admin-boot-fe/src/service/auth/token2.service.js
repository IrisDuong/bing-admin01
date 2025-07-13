import {LOCAL_LOGIN_NAME} from "../../util/config";

const getLocalAccessToken = () =>{
    return JSON.parse(localStorage.getItem(LOCAL_LOGIN_NAME))?.accessToken
}

const getLocalRefreshToken = () =>{
    return JSON.parse(localStorage.getItem(LOCAL_LOGIN_NAME))?.refreshToken
}

const getLocalLoggedInfo = () =>{
    return JSON.parse(localStorage.getItem(LOCAL_LOGIN_NAME))
}

const updateLocalAccessToken = newAccessToken =>{
    let loggedInfo = JSON.parse(localStorage.getItem(LOCAL_LOGIN_NAME));
    loggedInfo.accessToken = newAccessToken;
    localStorage.setItem(LOCAL_LOGIN_NAME,loggedInfo);
}

const setLocalLoggedInfo = newLoggedInfo =>{
    localStorage.setItem(LOCAL_LOGIN_NAME,JSON.stringify(newLoggedInfo));
}

const deleteLocalLoggdInfo = () =>{
    localStorage.removeItem(LOCAL_LOGIN_NAME)
}

export {
        getLocalAccessToken,
        getLocalRefreshToken,
        getLocalLoggedInfo,
        updateLocalAccessToken,
        setLocalLoggedInfo,
        deleteLocalLoggdInfo
}