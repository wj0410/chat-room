import { localCache } from '@/util/cache'
import ChatMiddleProp from '@/prop/ChatMiddleProp'
import MessageProp from '@/prop/MessageProp'

const chatMiddleCacheKey = 'CHAT_MIDDLE'
const msgCacheKey = 'MSG_CACHE_'
export const useChatMiddleCache = () => {
    const getChatMiddle = (): Array<ChatMiddleProp> => {
        return localCache.getJSON(chatMiddleCacheKey) as Array<ChatMiddleProp> ?? [];
    }
    const addChatMiddle = (value: ChatMiddleProp): void => {
        let cachedData: Array<ChatMiddleProp> = getChatMiddle()
        if (cachedData) {
            cachedData.push(value)
        } else {
            cachedData = [value] as Array<ChatMiddleProp>
        }
        localCache.setJSON(chatMiddleCacheKey, cachedData)
    }
    const removeChatMiddle = (value: ChatMiddleProp): void => {
        const cachedData: Array<ChatMiddleProp> = getChatMiddle()
        const newData: Array<ChatMiddleProp> = cachedData.filter(item => item.id !== value.id);
        localCache.setJSON(chatMiddleCacheKey, newData);
    }
    return { getChatMiddle, addChatMiddle, removeChatMiddle }
}
export const useMsgCache = () => {
    const getMsg = (key: any): Array<MessageProp> => {
        return localCache.getJSON(msgCacheKey + key) ?? [];
    }
    const addMsg = (key: string, value: MessageProp): void => {
        let cachedData = getMsg(key)
        if (cachedData) {
            cachedData.push(value)
        } else {
            cachedData = [value]
        }
        localCache.setJSON(msgCacheKey + key, cachedData)
    }
    return { getMsg, addMsg }
}