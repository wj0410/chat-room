// import User from "@/model/User";

// export default class DataCache {
//     /**
//      * 当前在线用户缓存，key -> username
//      */
//     public static USER_MAP: Map<string, User> = new Map<string, User>();

//     public static addOnlineUser(user: User) {
//         DataCache.USER_MAP.set(user.username, user);
//     }

//     public static removeOnlineUser(user: User) {
//         DataCache.USER_MAP.delete(user.username);
//     }

//     public static getUser(username: string): User | undefined {
//         return DataCache.USER_MAP.get(username);
//     }

//     public static getOnlineUserTotal(): number {
//         return DataCache.USER_MAP.size;
//     }
// }