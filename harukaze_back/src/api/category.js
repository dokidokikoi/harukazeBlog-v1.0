import request from '@/request'

export function getCategoryList() {
    return request.get('category')
}

export function getCategoryReport() {
    return request.get('admin/category/report')
}

export function saveCategory(param) {
    return request.post('admin/category', param)
}

export function editCategory(param) {
    return request.put('admin/category', param)
}

export function deleteCategory(id) {
    return request.delete(`admin/category/${id}`)
}