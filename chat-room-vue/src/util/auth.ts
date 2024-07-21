import { localCache } from './cache'
const TokenKey = 'token'

export function getToken() {
    return localCache.get(TokenKey)
}

export function setToken(token: string) {
    return localCache.set(TokenKey, token)
}

export function removeToken() {
    return localCache.remove(TokenKey)
}
