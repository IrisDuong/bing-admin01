import {ACCESS_TOKEN, REFRESH_TOKEN} from "../../util/config";

const getAccessToken = ()=>{
        let accessToken = localStorage.getItem(ACCESS_TOKEN);
        if(accessToken != null) return accessToken;
        return null;
}

const storeAccessToken = async token => {
    localStorage.setItem(ACCESS_TOKEN, token);
}
const deleteAccessToken = () =>{
   localStorage.removeItem(ACCESS_TOKEN)
}

const storeRefreshToken = async refreshToken => {
    localStorage.setItem(REFRESH_TOKEN, refreshToken);
}

const getRefreshToken = ()=>{
        let refreshToken = localStorage.getItem(REFRESH_TOKEN);
        if(refreshToken != null) return refreshToken;
        return null;
}
const deleteRefreshToken = ()=>{
    localStorage.removeItem(REFRESH_TOKEN);
}

export {
    getAccessToken,
    storeAccessToken,
    deleteAccessToken,
    getRefreshToken,
    storeRefreshToken,
    deleteRefreshToken
}