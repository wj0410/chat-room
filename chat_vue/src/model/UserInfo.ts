interface UserInfo {
    id?: string
    clientId?: string
    account: string // 账号
    realName?: string // 真实姓名
    nickName: string // 用户昵称
    password?: string // 密码
    sex?: number // 性别 1.男 2.女 3.未知
    email?: string // 邮箱
    phone?: string // 手机号
    avatar: string // 头像地址
    state?: number // 用户状态 1.正常 2.冻结
}
