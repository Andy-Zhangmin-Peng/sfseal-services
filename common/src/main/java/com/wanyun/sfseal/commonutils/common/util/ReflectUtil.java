/*
 * Copyright 2018 WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.commonutils.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @param <T>
 * @author WanYun
 */
public class ReflectUtil<T> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Get getter
     *
     * @param claz      the calz
     * @param fieldName the fieldName
     * @return Method
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Method getGetter(Class claz, String fieldName) {
        StringBuilder sb = new StringBuilder();
        sb.append("get").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1));
        try {
            Class[] types = new Class[]{};
            return claz.getMethod(sb.toString(), types);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * Get setter
     *
     * @param claz      the claz
     * @param fieldName the fieldName
     * @return Method
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Method getSetter(Class claz, String fieldName) throws NoSuchFieldException, NoSuchMethodException {
        Class[] parameterTypes = new Class[1];
        Field field = claz.getDeclaredField(fieldName);
        parameterTypes[0] = field.getType();
        String sb = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        return claz.getMethod(sb, parameterTypes);
    }

    /**
     * invoke setter
     *
     * @param t
     * @param fieldName
     * @param value
     */
    public void invokeSetter(T t, String fieldName, Object value) throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = getSetter(t.getClass(), fieldName);
        method.invoke(t, value);
    }

    /**
     * invoke getter
     *
     * @param t
     * @param fieldName
     * @return
     */
    public Object invokeGetter(T t, String fieldName) throws InvocationTargetException, IllegalAccessException {
        Method method = getGetter(t.getClass(), fieldName);
        return method.invoke(t);
    }
}
