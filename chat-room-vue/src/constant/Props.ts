import type User from "@/model/User";

interface ChatMiddleProp {
  type: "chat";
  avatar: string;
  unread: number;
  name: string;
  news: string;
}
interface GameCenterMiddleProp {
  type: "gameCenter";
  avatar: string;
  title: string;
}

interface ChatViewProp {
  headProp: HeadProp;
}
interface HeadProp {
  title: string;
  userCount?: number;
  groupUserList?: Array<User>;
}

export type { ChatMiddleProp, GameCenterMiddleProp, ChatViewProp, HeadProp };
