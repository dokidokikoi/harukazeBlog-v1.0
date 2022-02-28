import request from '@/request'

export function getLogs(page) {
    return request.get('admin/logs', {params: page})
}

export function deleteLog(id) {
    return request.delete(`admin/logs/${id}`)
}