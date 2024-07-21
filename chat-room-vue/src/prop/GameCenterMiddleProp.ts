import type { IMiddleProp } from "./interfaces/IProps";
class GameCenterMiddleProp implements IMiddleProp {
    public id: string;
    public avatar: string;
    public title: string;
    public type: string;

    constructor(id: string, avatar: string, title: string) {
        this.id = id;
        this.avatar = avatar;
        this.title = title;
        this.type = 'gameCenter'
    }
}
export default GameCenterMiddleProp;