import mitt, {Emitter, EventType} from 'mitt'
import useStore from "../store/store.ts";
import moment from "moment";


export const $mitt: Emitter<Record<EventType, unknown>> = mitt()

export const $store = useStore()

export const $moment = moment()



