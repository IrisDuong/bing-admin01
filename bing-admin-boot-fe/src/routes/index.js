import Product from "../components/warehouse/product/Product";
import Supplier from "../components/partner/supplier/Supplier";
import ProductClassification from "../components/warehouse/productClassification/ProductClassification";
import NotFound from '../components/common/NotFound';
import BaseCode from "../components/setting/base-code/BaseCode";
// import TestAllUser from "../components/user/TestAllUser";
// import TestUserByAdmin from "../components/user/TestUserByAdmin";
// import TestUserByMod from "../components/user/TestUserByMod";
// import TestUserByStaff from "../components/user/TestUserByStaff";
import _403Page from "../components/auth/_403Page";
export const routes = [
    {
        path : "/product",
        element : <Product></Product>
    },
    {
        path : "/productCategory",
        element : <ProductClassification></ProductClassification>
    },
    {
        path : "/supplier",
        element : <Supplier></Supplier>
    },
    // {
    //     path : "/user/all",
    //     element : <TestAllUser></TestAllUser>
    // },
    // {
    //     path : "/user/admin",
    //     element : <TestUserByAdmin></TestUserByAdmin>
    // },
    // {
    //     path : "/user/mod",
    //     element : <TestUserByMod></TestUserByMod>
    // },
    // {
    //     path : "/user/staff",
    //     element : <TestUserByStaff></TestUserByStaff>
    // },
    {
        path : "/inStocks",
        errorElement : <NotFound></NotFound>
    },
    {
        path : "/baseCode",
        element : <BaseCode></BaseCode>
    },
    {
        path : "/403",
        element : <_403Page></_403Page>
    }
]