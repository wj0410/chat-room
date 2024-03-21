import type User from "@/model/User";

class MyWebSocket extends WebSocket {

    constructor(user: User, url: string) {
        url = url + "?username=" + user.username;
        super(url)
    }

}

export default MyWebSocket