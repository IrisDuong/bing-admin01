import React,{useEffect, useState} from "react";
import {testUser} from "../../service/user/user.service";

const TestUserByMod = props =>{
    const [data, setData] = useState("");
    useEffect(()=>{
        testUser("/api/test-user/testByMod")
        .then(res=>{
            setData(res.data)
        })
        .catch(error => console.log("test by admin error = ", error))
    },[])
    return(
        <>
            <h1>{data}</h1>
        </>
    )
}

export default TestUserByMod;