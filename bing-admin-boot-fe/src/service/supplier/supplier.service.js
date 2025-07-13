import api from "../../util/axios-config";

const searchSuppliers = async param =>{
    try {
        let response = await api.post(`/wh/productSupplier/search`,param);
        return response.data;
    } catch (error) {
        return null;
    }
}

const createSupplier = async param =>{
    try {
        api.post(`/wh/productSupplier/create`,param);
        return true;
    } catch (error) {
        return false;
    }
}
export{
    searchSuppliers,
    createSupplier
}