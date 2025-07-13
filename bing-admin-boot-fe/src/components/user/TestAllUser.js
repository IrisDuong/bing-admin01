import React,{useEffect, useState} from "react";
import api from "../../util/axios-config";

const TestAllUser = props =>{
    const [data, setData] = useState("vxvZv");
    const isMale = false;
    useEffect(()=>{
        // api.get("/api/test-user/all")
        // .then(response=> setData(response.data))
    },[])
    return(
        <>
            <h1>This is {isMale && 'Male'} {!isMale && 'Female'}</h1>
            <h1>That person is {isMale ? 'Male' : 'Female'}</h1>
        </>
    )
}

export default TestAllUser;