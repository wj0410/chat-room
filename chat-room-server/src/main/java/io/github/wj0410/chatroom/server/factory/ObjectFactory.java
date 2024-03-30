package io.github.wj0410.chatroom.server.factory;


import io.github.wj0410.chatroom.server.service.AbstractResponseHistoryService;
import io.github.wj0410.chatroom.server.service.DefaultResponseHistoryService;
import io.github.wj0410.cloudbox.tools.factory.AbstractSingletonFactory;

/**
 * @author anlingyi
 * @date 2021/9/11 6:38 下午
 */
public class ObjectFactory extends AbstractSingletonFactory<Class, Object> {

    private static final ObjectFactory INSTANCE = new ObjectFactory();

    private ObjectFactory() {
    }

    @Override
    protected void registration(Registry<Class, Object> registry) {
        registry.add(AbstractResponseHistoryService.class, DefaultResponseHistoryService.class);
    }

    public static <T> T getObject(Class<T> key) {
        return (T) INSTANCE.produce(key);
    }

}
