import axios from 'axios'
// import { Message } from 'element-ui'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

const http = axios.create({
    baseURL: 'https://harukaze.top:8081/',
    timeout: 50000
})

http.interceptors.request.use(config => {
    NProgress.start()
    return config
}, err => {
    Promise.reject(err)
})

http.interceptors.response.use(
    response => {
        NProgress.done()
        const res = response.data;
        if (res.code !== 200) {
            console.log("oh shit!!!")
            if (res.code === 400) {
                ELEMENT.Message.error(res.msg)
                return Promise.reject('error1');
            }
            if (res.code === 403) {
                ELEMENT.Message.error(res.msg)
                return Promise.reject('error2');
            }
            return Promise.reject('error3');
        } else {
            return response;
        }
    },
    err => {
        NProgress.done()
        ELEMENT.Message.error("连接超时")
        return Promise.reject(err);
    }
)

export default http