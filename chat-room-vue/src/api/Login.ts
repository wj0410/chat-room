import Request from '@/util/Request'
import LoginDTO from '@/model/LoginDTO'
import RegisterDTO from '@/model/RegisterDTO'

// 登录方法
export function login(loginDTO: LoginDTO) {

    return Request({
        url: '/auth/login',
        headers: {
            isToken: false
        },
        method: 'post',
        data: loginDTO
    })
}

// 注册方法
export function register(registerDTO: RegisterDTO) {
    return Request({
        url: '/auth/register',
        headers: {
            isToken: false
        },
        method: 'post',
        data: registerDTO
    })
}

// 获取用户详细信息
export function getInfo() {
    return Request({
        url: '/system/user/getInfo',
        method: 'get'
    })
}

// 退出方法
export function logout() {
    return Request({
        url: '/auth/logout',
        method: 'post'
    })
}