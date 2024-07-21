# chat-room

#### 介绍
    Netty聊天室

#### 软件架构
1. chat-room-common 公共模块
2. chat-room-client 基于Java Swing的客户端
3. chat-room-server 基于Java Netty框架的服务端
4. chat-room-system 系统服务，提供用户注册登录等服务
5. chat-room-api 接口服务，暴露接口调用的api服务

#### 使用说明
1. server.yml 和 client.yml 里的客户端版本要一致
2. 启动chat-room-server服务端
3. 启动chat-room-client
