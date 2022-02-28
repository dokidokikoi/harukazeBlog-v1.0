import request from '@/request'

export function getUserInfo() {
    return request.get('admin/users/currentUser')
}

export function saveUserInfo(info) {
    return request.put("admin/users/currentUser", info)
}

export function getUserList(page) {
    return request.get('admin/users', {params: page})
}

export function addUser(info) {
    return request.post('admin/users', info)
}

export function editUser(info) {
    return request.put('admin/users', info)
}

export function deleteUser(id) {
    return request.delete(`admin/users/${id}`)
}

export function cancelDelete(id) {
    return request.put(`admin/users/${id}`)
}

export function editPwd(info) {
    return request.put("admin/users/currentUser/pwd", info)
}