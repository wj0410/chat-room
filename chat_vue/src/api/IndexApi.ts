import {httpRequest, changeParam} from '../service/service'

export const loginApi: any = {
    loginConnect: (data) => {
        return httpRequest({
            url: 'http://47.98.35.95:5678' + changeParam(data),
            method: 'get',
        })
    },
}

export const userApi: any = {
    selectById: () => {

    },
    updateById: () => {

    },
    pageQuery() {

    }
}
