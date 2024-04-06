import {$mitt} from "@/utils/MyCommon";
import {MittConstants} from "../constant/constants";
const ALIVE = 'isConnectionAlive'

export function showOffline(): void {
  if(isConnectionAlive()){
    setOffline()
    $mitt.emit(MittConstants.SHOW_OFFLINE,true);
  }
}
export function hideOffline(): void {
  if(!isConnectionAlive()){
    setAlive()
    $mitt.emit(MittConstants.SHOW_OFFLINE,false);
  }
}

export function isConnectionAlive() {
  return sessionStorage.getItem(ALIVE) === "TRUE";
}

export function setAlive() {
  return sessionStorage.setItem(ALIVE,"TRUE")
}

export function setOffline() {
  return sessionStorage.setItem(ALIVE,"FALSE")
}
