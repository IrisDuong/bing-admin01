import {useRouteError} from "react-router-dom"
const NotFound = () =>{
    const error = useRouteError();
    return(
        <>
            <h2>Oops !</h2>
            <p className="404-msg">Xin lỗi, trang hiện tại chưa phát triển</p>
            <i>{error.statusText || error.message}</i>
            <p className="404-msg">Vui lòng quay lại sau. Xin cảm ơn</p>
        </>
    )
}
export default NotFound;