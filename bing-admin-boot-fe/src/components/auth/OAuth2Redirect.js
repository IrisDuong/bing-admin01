import React from "react";
import {useSearchParams, Navigate} from "react-router-dom";
import {ACCESS_TOKEN,GOOGLE_AUTH_URL} from "../../util/config"
const OAuth2Redirect = () =>{
    // const [searchParams] = useSearchParams();
    // const getUrlParameter = () => {
    //     // name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    //     // var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');

    //     // var results = regex.exec(this.props.location.search);
    //     // console.log(">>>> location : "+this.props.location);
    //     // return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
    //     let token = searchParams.get("token");
    //     console.log("access token ", token);
    //     return token
    // };
    // var accessToken = getUrlParameter();
    // if(accessToken){
    //     localStorage.setItem(ACCESS_TOKEN, accessToken)
    //     return <Navigate to="/"></Navigate>
    // }else{
    //     return <Navigate  to="/login"></Navigate>
    // }
    return(
        <>OAuth 2 user logged successfully</>
    )
}
export default OAuth2Redirect;