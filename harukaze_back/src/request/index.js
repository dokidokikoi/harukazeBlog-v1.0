import axios from 'axios'
import { getToken } from '@/request/token'
import NProgress from 'nprogress'
import { Message } from 'element-ui'

const http = axios.create({
    // baseURL: 'https://harukaze.top:8081/',
    baseURL: 'https://localhost:8081/',
    timeout: 50000
})

http.interceptors.request.use(config => {
    NProgress.start()
    config.headers.Authorization = getToken()
    return config
}, err => {
    Promise.reject("见鬼")
})

http.interceptors.response.use(
    response => {
        NProgress.done()
        const res = response.data;
        if (res.code !== 200) {
            if (res.code === 400) {
                // ELEMENT.Message.error(res.msg)
                Message.error(res.msg)
                return Promise.reject('error1');
            }
            if (res.code === 403) {
                // ELEMENT.Message.error(res.msg)
                Message.error(res.msg)
                return Promise.reject('error2');
            }
            return Promise.reject('error3');
        } else {
            return response;
        }
    },
    err => {
        NProgress.done()
        // ELEMENT.Message.error("连接超时")
        Message.error("连接超时")
        return Promise.reject(err);
    }
)

export default http