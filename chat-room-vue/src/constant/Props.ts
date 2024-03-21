import type User from "@/model/User";

interface ChatMiddle {
  type: "chat";
  avatar: string;
  unread: number;
  name: string;
  news: string;
}
interface GameCenterMiddle {
  type: "gameCenter";
  avatar: string;
  title: string;
}

interface ChatView {
  head: Head;
}
interface Head {
  title: string;
  userCount: number;
  groupUserList: Array<User>;
}

export type { ChatMiddle, GameCenterMiddle, ChatView, Head };
