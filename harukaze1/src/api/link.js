import request from '@/request'

export function getLink() {
    return request.get('link')
}