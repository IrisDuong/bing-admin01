import axios from "../../util/axios-config";
import {CURRENT_LANG_CODE} from "../../util/config";
const getListCommonCodeByWorkCode = async  (workCode) => {
    var listCommonCodes = [];
    try {
        listCommonCodes = await axios.get(`/baseCodeController/commonCode/getListCommonCodeByWorkCode/${workCode}?langCode=${CURRENT_LANG_CODE}`);
    } catch (error) {
        return error;
    }
    return listCommonCodes ? listCommonCodes.data.listCommonCodes:[]
}

const createCommonCode = async (param) => {
    try {
       await axios.post('/baseCodeController/commonCode/create',param)
    } catch (error) {
        
    }
}

const getListGeneralCodeByCommonCode = async ({commonCode,langCode}) => {
    try {
        var listGeneralCodes = await axios.get(`/baseCodeController/generalCode/getAllByCommonCode?commonCode=${commonCode}&langCode=${langCode}`);
    } catch (error) {
        return error;
    }
    return listGeneralCodes ? listGeneralCodes.data.listGeneralCodes:[]
}
const createGeneralCode = async (listParams) => {
    try {
        await axios.post('/baseCodeController/generalCode/create',listParams);
    } catch (error) {
        console.log("error in ceate general code");
        console.log(error);
    }
}
export{
    getListCommonCodeByWorkCode,
    createCommonCode,
    getListGeneralCodeByCommonCode,
    createGeneralCode
}
// class BaseCodeService{
//     async  getListCommonCodeByWorkCode(workCode){
//         var listCommonCodes = [];
//         try {
//             listCommonCodes = await axios.get(`/baseCodeController/commonCode/getListCommonCodeByWorkCode/${workCode}?langCode=en`);
//         } catch (error) {
//             return error;
//         }
//         return listCommonCodes ? listCommonCodes.data.listCommonCodes:[]
//     }

//     async createCommonCode(param){
//         try {
//            await axios.post('/baseCodeController/commonCode/create',param)
//         } catch (error) {
            
//         }
//     }

//     async getListGeneralCodeByCommonCode({commonCode,langCode}){
//         try {
//             var listGeneralCodes = await axios.get(`/baseCodeController/generalCode/getAllByCommonCode?commonCode=${commonCode}&langCode=${langCode}`);
//         } catch (error) {
//             return error;
//         }
//         return listGeneralCodes ? listGeneralCodes.data.listGeneralCodes:[]
//     }
//     async createGeneralCode(listParams){
//         try {
//             await axios.post('/baseCodeController/generalCode/create',listParams);
//         } catch (error) {
//             console.log("error in ceate general code");
//             console.log(error);
//         }
//     }
// }

// export default new BaseCodeService();