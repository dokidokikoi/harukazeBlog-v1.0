import request from '@/request'

export function getArticleList(page) {
    return request.get('articles',{params: page})
}

export function publishArticle(article) {
    return request.post('admin/articles',article)
}

export function getArticleById(id) {
    return request.get(`articles/${id}`)
}

export function deleteById(id) {
    return request.delete(`admin/articles/${id}`)
}

export function getDeleted(page) {
    return request.get(`admin/articles/deleted`,{params: page})
}

export function recoverArticle(id) {
    return request.put(`admin/articles/deleted/${id}`)
}