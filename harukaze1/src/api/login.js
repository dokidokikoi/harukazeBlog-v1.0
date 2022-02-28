import request from '@/request'
import axios from 'axios'
import qs from 'qs'

export function login(user) {
    return request({
        url: 'login',
        method: 'POST',
        headers: { 'content-type': 'application/x-www-form-urlencoded' },
        data: qs.stringify(user)
      }) 
  
}

export function logout() {
  return request.post('logout')
}