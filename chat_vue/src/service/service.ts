import axios, {AxiosInstance} from "axios";

const service: AxiosInstance = axios.create({
    timeout: 30000,
    baseURL: '/api',
    headers: {
        'Content-Type': 'application/json;charset=utf-8',
    },
})
service.interceptors.request.use(
    async request => {
        return request
    },
    error => {
        return Promise.reject(error);
    }
)

service.interceptors.response.use(
    response => {

    },
    error => {

    }
)
//post请求
const httpRequest = (config: axios.CreateAxiosDefaults) => {
    return new Promise<axios.AxiosResponse<any>>((resolve, reject) => {
        service({
            ...config,
        }).then(res => {
            resolve(res)
        }).catch(error => {
            reject(error)
        })
    })
}
const changeParam = (param) => {
    if (param === null || param === {} || param === undefined) {
        return ''
    }
    return JSON.stringify(param)
        .replace(/:/g, '=')
        .replace(/,/g, '&')
        .replace(/{/g, '?')
        .replace(/}/g, '')
        .replace(/"/g, '')
}
export {httpRequest, changeParam}

