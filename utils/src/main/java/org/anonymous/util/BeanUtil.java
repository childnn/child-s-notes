package org.anonymous.util;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.beans.BeanMap;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author MiaoOne
 * 2019/8/7 10:35
 * * bean 和 map 的相互转换
 * * 1. 实体类必须要有 setter 方法
 * * 2. 使用 lombok 不可以开启 链式 set 模式: 即不能使用 @Accessors(chain = true)
 */
public final class BeanUtil {

    /**
     * bean copy: 将集合中每个对象的属性封装到另一个类的对象的属性
     *
     * @param sourceList  数据源集合
     * @param targetClass 目标类的 Class 对象
     * @param <S>         source 泛型
     * @param <T>         target 泛型
     * @return targetList
     */
    public static <S, T> List<T> copyBeanList(List<S> sourceList, Class<T> targetClass) {
        if (sourceList == null || sourceList.isEmpty() || targetClass == null) {
            return Collections.emptyList();
        }
        List<T> destList = new ArrayList<>();
        Class sourceClass = sourceList.get(0).getClass();
        BeanCopier beanCopier = BeanCopier.create(sourceClass, targetClass, false);
        for (S sourceObj : sourceList) {
            T destObj = null;
            try {
                destObj = targetClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            beanCopier.copy(sourceObj, destObj, null);
            destList.add(destObj);
        }
        return destList;
    }

    /**
     * //必须要有 get 方法: 需要从给定的 bean 中获取属性
     *
     * @param bean object
     * @param <T>  type of the bean
     * @return map of the bean: key -- 属性名, value -- 属性值
     */
    public static <T> Map<String, Object> bean2Map(T bean) {
        Map<String, Object> map = new HashMap<>();
        if (null != bean) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * //必须要有 set 方法: 否则属性就是 默认值
     *
     * @param map   key 属性名, value 属性值
     * @param clazz the class of the entity
     * @param <T>   entity type
     * @return bean
     */
    public static <T> T map2Bean(Map<String, Object> map, Class<T> clazz) {
        T bean = null;
        try {
            bean = clazz.getConstructor().newInstance();
            map2Bean(map, bean);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

//        BeanMap beanMap = BeanMap.create(bean);
//        beanMap.putAll(map);
        return bean;
    }

    public static <T> T map2Bean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    public static <T> List<Map<String, Object>> objectsToMaps(List<T> objects) {
        final List<Map<String, Object>> list = new ArrayList<>();
        objects.forEach(bean -> list.add(bean2Map(bean)));
        return list;
    }

    public static <T> List<T> maps2Objects(List<Map<String, Object>> maps, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        if (maps != null && maps.size() > 0) {
//            Map<String, Object> map = null;
            for (Map<String, Object> beanMap : maps) {
                T instance = null;
                try {
                    instance = clazz.getConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
                T bean = map2Bean(beanMap, instance);
                list.add(bean);
            }
        }
        return list;
    }

}
