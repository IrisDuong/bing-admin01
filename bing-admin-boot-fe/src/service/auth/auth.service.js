import api from "../../util/axios-config";
import {getAccessToken, storeAccessToken, storeRefreshToken, deleteAccessToken, deleteRefreshToken} from "./token.service";
const login = async param =>{
    try {
        var response = await api.post(`/auth/login`,param);
        var data = response.data;
        if(data != null){
            storeAccessToken(data.accessToken);
            storeRefreshToken(data.refreshToken);
            let loggedUser = {
                                email: data.email,
                                userName : data.userName,
                                fullName : data.fullName
                            }
            return loggedUser;
        }
        return null;
    } catch (error) {
        console.log(">>>>>> Login failed in front-end");
        return null;
    }
}
const getLoggedUser = async () =>{
    try {
        let accessToken =  getAccessToken();
        var response = await api.get("/auth/getCurrentLoggedInfo");
        if(response.status === 204) return null;
        var user = response.data
        return user;
    } catch (error) {
        return null;
    }
}
const logout = async ()=>{
    try {
        let response = await api.get("/logout");
        deleteAccessToken();
        deleteRefreshToken()
        return true;
    } catch (error) {
        return false;
    }
}
export {
    getLoggedUser,
    login,
    logout
}