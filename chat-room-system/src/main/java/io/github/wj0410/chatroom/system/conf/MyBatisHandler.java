package io.github.wj0410.chatroom.system.conf;

import io.github.wj0410.cloudbox.easycrud.mp.handler.AbstractMetaObjectHandler;
import io.github.wj0410.cloudbox.tools.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjie
 */
@Component
public class MyBatisHandler extends AbstractMetaObjectHandler {
    @Override
    protected Long getLoginUserId() {
        return SecurityContextHolder.getUserId();
    }

    @Override
    protected String getLoginUserName() {
        return SecurityContextHolder.getUserName();
    }

    @Override
    protected List<Class> needInsertIdClasses() {
        return new ArrayList<>();
    }
}
