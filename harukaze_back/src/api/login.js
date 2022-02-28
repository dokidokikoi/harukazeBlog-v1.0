import request from '@/request'
import qs from 'qs'

export function login(user) {
    // return request({
    //     // url: 'https://harukaze.top:8081/login',
    //     url: 'https://localhost:8081/login',
    //     method: 'POST',
    //     headers: { 'content-type': 'application/x-www-form-urlencoded' },
    //     data: qs.stringify(user)
    //   }) 
    return request.post('login', user)
}

export function logout() {
  return request.post('logout')
}