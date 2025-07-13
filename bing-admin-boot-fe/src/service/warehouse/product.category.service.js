import api from "../../util/axios-config";
const searchProductCategories = async param =>{
    try {
        var response = await api.post("/productCategoryController/search", param);
        return response.data.listProductCategories;
    } catch (error) {
        return [];
    }
}

export {
    searchProductCategories
}