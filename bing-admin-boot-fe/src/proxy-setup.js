import {createProxyMiddleware} from "http-proxy-middleware";
module.exports = app =>{
    app.use(
        "/",
        createProxyMiddleware({
            target : 'http://localhost:8086',
            changeOrigin :  true,
        })
    )
}