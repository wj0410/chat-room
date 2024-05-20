package io.github.wj0410.chatroom.server.factory;

import io.github.wj0410.chatroom.common.enums.Action;
import io.github.wj0410.chatroom.server.action.handler.base.ActionHandler;
import io.github.wj0410.chatroom.server.annotation.DoAction;

/**
 * @author anlingyi
 * @date 2021/8/21 7:07 下午
 */
public class ActionHandlerFactory extends AbstractSingletonFactory<Action, ActionHandler> {

    public static final ActionHandlerFactory INSTANCE = new ActionHandlerFactory();

    private ActionHandlerFactory() {
    }

    @Override
    protected void registration(Registry<Action, ActionHandler> registry) {
        registry.addByAnnotation(DoAction.class);
    }

}
