import { MessageContainerType} from "../constant/Enum";

export class MessageContainer {
    type :MessageContainerType
    text: string
    imageData: Array<number>;

    constructor(data) {
        this.type = data.type;
        this.text = data.text;
        this.imageData = data.imageData;
    }

}
