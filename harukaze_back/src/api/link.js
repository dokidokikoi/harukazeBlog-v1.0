import request from '@/request'

export function getLinks() {
    return request.get('link')
}

export function saveLink(info) {
    return request.post('admin/link', info)
}

export function changeLink(info) {
    return request.put('admin/link', info)
}

export function deleteLink(id) {
    return request.delete(`admin/link/${id}`)
}