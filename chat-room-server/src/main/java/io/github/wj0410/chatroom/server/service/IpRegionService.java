package io.github.wj0410.chatroom.server.service;


import io.github.wj0410.chatroom.common.entity.IpRegion;

/**
 * ip地址服务
 *
 * @author nn200433
 * @date 2022年01月10日 0010 11:35:30
 */
public interface IpRegionService {

    /**
     * 获取地区
     *
     * @param ip ip地址
     * @return {@link IpRegion }
     * @author nn200433
     */
    public IpRegion getRegion(String ip);

}
