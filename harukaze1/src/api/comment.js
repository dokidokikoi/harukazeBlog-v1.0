import request from '@/request'

export function getCommentByArticleId(id, page) {
    return request.get(`comments/${id}`,{params: page})
}

export function recover(data) {
    return request.post('admin/comment', data)
}