import axios from "axios";
import {useEffect} from "react"
import {useNavigate} from "react-router-dom"
import {BASE_API_URL} from "./config"
import {getAccessToken, getRefreshToken, storeAccessToken} from "../service/auth/token.service"
const axiosInstance = axios.create({
    baseURL : BASE_API_URL,
    headers:{
        "Content-Type":"application/json",
        "Access-Control-Allow-Origin":"*"
    }
})


axiosInstance.interceptors.request.use(
    config => {
        let accessToken = getAccessToken();
        if(accessToken){
            config.headers["Authorization"] = "Bearer "+accessToken;
        }
        return config;
    },
    error => {
        return Promise.reject(error)
    }
)

const AxiosInterceptor = ({children}) =>{
    const navigate = useNavigate();
    useEffect(()=>{
        const resInterceptor = res =>{
            return res;
        }

        const errResInterceptor = async error =>{
            const {config, response} = error;
            if(config.url !== BASE_API_URL + "/auth/login" && response){
                if(response.status === 403  && !config._retry){
                    navigate("/403")
                }else if(response.status === 401 && !config._retry){
                    // navigate("/login",{state:{unauthorizedError:"401"}})
                    config._retry = true;
                    try {
                        const _response = await axiosInstance.post("/auth/refreshToken",{refreshToken:getRefreshToken()})
                        var data = _response.data;
                        if(data != null) storeAccessToken(data.accessToken);
                        return axiosInstance(config);
                    } catch (_error) {
                        return Promise.reject(_error);
                    }
                }
            }
            return Promise.reject(error);
        }

        const interceptor =   axiosInstance.interceptors.response.use(resInterceptor, errResInterceptor);
        return () => axiosInstance.interceptors.response.eject(interceptor)
    },[navigate]);

    return children;
}

export default axiosInstance;
export {AxiosInterceptor}