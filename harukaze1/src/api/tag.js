import request from '@/request'

export function getTags() {
    return request.get('tags')
}

export function tagReport() {
    return request.get('admin/tags/report')
}

export function saveTag(param) {
    return request.post('admin/tags', param)
}

export function editTag(param) {
    return request.put('admin/tags', param)
}

export function deleteTag(id) {
    return request.delete(`admin/tags/${id}`)
}

export function getTag(id) {
    return request.get(`tags/${id}`)
}

export function getTagCounts() {
    return request.get('tags/counts')
}