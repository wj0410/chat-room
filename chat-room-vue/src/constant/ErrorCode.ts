enum ResponseCode {
    SUCCESS = 200,
    FAILURE = 400,
    UNAUTHORIZED = 401,
    SYSTEM_ERROR = 500
}

const ErrorCode: Record<ResponseCode, string> = {
    [ResponseCode.SUCCESS]: '操作成功',
    [ResponseCode.FAILURE]: '业务异常',
    [ResponseCode.UNAUTHORIZED]: 'token已过期或验证不正确',
    [ResponseCode.SYSTEM_ERROR]: '系统未知错误，请反馈给管理员'
};
export { ResponseCode, ErrorCode };