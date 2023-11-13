package io.github.wj0410.chatroom.system.conf;

import io.github.wj0410.cloudbox.easycrudmp.handler.AbstractMetaObjectHandler;
import org.springframework.stereotype.Component;

/**
 * @author wangjie
 */
@Component
public class MyBatisHandler extends AbstractMetaObjectHandler {
    @Override
    protected Long getLoginUserId() {
        return 1L;
    }

    @Override
    protected String getLoginUserName() {
        return "系统管理员";
    }
}
