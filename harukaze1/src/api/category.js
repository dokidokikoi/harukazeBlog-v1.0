import request from '@/request'

export function getCategoryList() {
    return request.get('category')
}

export function getCategory(id) {
    return request.get(`category/${id}`)
}

export function getCategoryCounts() {
    return request.get('category/counts')
}