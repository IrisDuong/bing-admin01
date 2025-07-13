import api from "../../util/axios-config"
const testUser = url =>{
    return api.get(url);
}

export {
    testUser
}