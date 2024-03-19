import User from "@/model/User";

const TokenKey = 'token'
const UserKey = 'userInfo'

export function getToken() {
    return localStorage.getItem(TokenKey)
}

export function setToken(token) {
    return localStorage.setItem(TokenKey, token)
}

export function removeToken() {
    return localStorage.removeItem(TokenKey)
}

export function getLoginUser(): User | undefined {
    return new User(<string>localStorage.getItem(UserKey))
}

export function setLoginUser(json: string) {
    localStorage.setItem(UserKey, json)
}