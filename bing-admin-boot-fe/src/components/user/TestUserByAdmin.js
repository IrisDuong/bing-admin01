import React,{useEffect, useState} from "react";
import {testUser} from "../../service/user/user.service";


const TestUserByAdmin = props =>{
    const [data, setData] = useState("");
    const _testUser = (url)=>{
        testUser(url)
        .then(res=>{
            setData(res.data)
        })
        .catch(error => console.log("test by admin error = ", error))
    }
    useEffect(()=>{
        _testUser("/api/test-user/testByAdmin")
    },[])
    return(
        <>
            <h1>{data}</h1>
        </>
    )
}

export default TestUserByAdmin;