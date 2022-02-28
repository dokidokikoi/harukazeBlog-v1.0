import request from '@/request'

export function getArticleList(page) {
    return request.get('articles',{params: page})
}

export function getArticleById(id) {
    return request.get(`articles/${id}`)
}

export function getArchive() {
    return request.get('articles/archives')
}

export function getArtilceCounts() {
    return request.get('articles/counts')
}