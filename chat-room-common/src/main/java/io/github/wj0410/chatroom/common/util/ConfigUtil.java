package io.github.wj0410.chatroom.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;

/**
 * @author wangjie
 * @date 2023/11/2
 */
public class ConfigUtil {
    public static <T> T loadYaml(String fileName, Class<T> targetClass) {
        URL resource = ConfigUtil.class.getClassLoader().getResource(fileName);

        if (resource == null) {
            throw new IllegalArgumentException("YAML file not found: " + fileName);
        }
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            return mapper.readValue(resource, targetClass);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                Constructor<?> constructor = targetClass.getDeclaredConstructor();
                // 如果构造方法不可访问，需要设置为可访问
                constructor.setAccessible(true);
                return (T) constructor.newInstance();
            } catch (Exception ex) {
                return null;
            }
        }
    }
}
