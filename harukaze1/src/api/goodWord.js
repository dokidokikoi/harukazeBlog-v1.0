import request from '@/request'

export function getWord() {
    return request.get('goodWords')
}