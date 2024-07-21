package io.github.wj0410.chatroom.server.annotation;



import io.github.wj0410.chatroom.common.enums.Action;

import java.lang.annotation.*;

/**
 * @author anlingyi
 * @date 2021/9/19 3:43 下午
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DoAction {

    Action value();

}
