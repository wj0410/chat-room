package io.github.wj0410.chatroom.server.service;


import io.github.wj0410.chatroom.server.conf.IpRegionProperties;

/**
 * IP地区抽象服务实现
 *
 * @author nn200433
 * @date 2022年01月10日 0010 15:51:33
 */
public abstract class AbstractIpRegionService {

    protected IpRegionProperties ipRegionProperties;

    public AbstractIpRegionService(IpRegionProperties ipRegionProperties) {
        this.ipRegionProperties = ipRegionProperties;
    }

}
