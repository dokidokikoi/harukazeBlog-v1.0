import request from '@/request'

export function getUserInfo() {
    return request.get('admin/users/currentUser')
}