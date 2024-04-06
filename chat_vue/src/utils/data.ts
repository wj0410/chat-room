import ChatModel from "../model/ChatModel";
import {MessageType} from "../constant/Enum";

const ChatModelListKey = 'ChatModelList'

export function getChatModelListData() {
  return localStorage.getItem(ChatModelListKey) ? JSON.parse(localStorage.getItem(ChatModelListKey)) : null
}

export function setChatModelListData(chatModelList:Array<ChatModel>) {
  return localStorage.setItem(ChatModelListKey,JSON.stringify(chatModelList))
}

export function removeChatModelList() {
  return localStorage.removeItem(ChatModelListKey)
}
