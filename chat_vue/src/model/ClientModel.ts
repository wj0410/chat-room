import {ClientOrigin, ClientType} from "../constant/Enum.ts";

interface ClientModel {
    account: string
    clientId: string
    clientOrigin: ClientOrigin
    clientType: ClientType
    nickName: string
    avatar: string
}

export default ClientModel
