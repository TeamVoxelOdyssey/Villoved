package com.guy7cc.villoved.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReflectionUtil {
    public static List<Object> collectFields(Object obj, Predicate<Object> cond, Logger logger){
        Class<?> clazz = obj.getClass();
        List<Object> list = new ArrayList<>();
        for(Field field : clazz.getDeclaredFields()){
            if(Modifier.isStatic(field.getModifiers())) continue;
            field.setAccessible(true);
            try {
                Object fieldObj = field.get(obj);
                if(cond.test(fieldObj)) list.add(fieldObj);
            } catch (IllegalAccessException e) {
                logger.log(
                        Level.SEVERE,
                        String.format("DataLoader detected illegal access to field %s, ignoring it", field.getName()),
                        e
                );
            }
        }
        return list;
    }

    public static List<Object> collectStaticFields(Class<?> clazz, Predicate<Object> cond, Logger logger){
        List<Object> list = new ArrayList<>();
        for(Field field : clazz.getDeclaredFields()){
            if(!Modifier.isStatic(field.getModifiers())) continue;
            field.setAccessible(true);
            try {
                Object fieldObj = field.get(null);
                if(cond.test(fieldObj)) list.add(fieldObj);
            } catch (IllegalAccessException e) {
                logger.log(
                        Level.SEVERE,
                        String.format("DataLoader detected illegal access to field %s, ignoring it", field.getName()),
                        e
                );
            }
        }
        return list;
    }
}
