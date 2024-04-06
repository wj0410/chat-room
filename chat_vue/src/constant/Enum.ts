export enum ChatType {
    PUBLIC = "PUBLIC",
    PRIVATE = "PRIVATE",
    GROUP = "GROUP"
}

export enum ClientOrigin {
    SWING = "SWING",
    WEBSOCKET = "WEBSOCKET"
}
export enum ClientType {
    PUBLIC = "PUBLIC",
    GROUP = "GROUP",
    PERSONAL = "PERSONAL",
}

export enum MessageContainerType {
    TEXT = "TEXT",
    IMAGE = "IMAGE"
};

export enum MessageType {
    BIND = "BIND",
    NORMAL = "NORMAL",
    SYNC_ONLINE = "SYNC_ONLINE",
    PROMPT = "PROMPT",
    REFUSE = "REFUSE",
};

export enum PromptType {
    WELCOME = "WELCOME",
    LEAVE = "LEAVE"
};
