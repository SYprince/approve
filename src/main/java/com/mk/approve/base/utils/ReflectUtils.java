package com.mk.approve.base.utils;

import javax.persistence.Id;
import java.lang.reflect.Field;

/**
 * @author 张庆贺
 * @since 2020/2/22 20:33
 */
public class ReflectUtils {

    /**
     * 获取主键名称
     *
     * @param clazz 类名
     * @return 主键名称
     */
    public static String getPrimaryKeyName(Class clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();

        for (Field field : declaredFields) {
            if (null != field.getAnnotation(Id.class)) {
                return field.getName();
            }
        }
        return "";
    }
}
