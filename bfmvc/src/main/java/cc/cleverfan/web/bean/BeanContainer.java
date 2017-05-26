package cc.cleverfan.web.bean;

import cc.cleverfan.web.annotation.Controller;
import cc.cleverfan.web.annotation.Service;
import cc.cleverfan.web.helper.ClassHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Bean容器
 */
public class BeanContainer {

    /**
     * 存放Bean类和Bean实例的映射关系
     */
    private static final Map<String, Object> BEAN_CONTAINER = new HashMap<>();

    static {
        ArrayList<Class<?>> beanClasses = ClassHelper.getBeanClasses();
        beanClasses
            .stream()
            .filter(beanClass -> beanClass.isAnnotationPresent(Controller.class) || beanClass.isAnnotationPresent(Service.class))
            .forEach(beanClass -> BEAN_CONTAINER.put(beanClass.getName(), BeanFactory.newInstance(beanClass)));
    }

    /**
     * 获取Bean映射
     *
     * @return
     */
    public static Map<String, Object> getBeanContainer() {
        return BEAN_CONTAINER;
    }

    /**
     * 获取Bean实例
     */

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String className) {
        if (!BEAN_CONTAINER.containsKey(className)) {
            throw new RuntimeException("can not get bean by className: " + className);
        }
        return (T) BEAN_CONTAINER.get(className);
    }

    /**
     * 设置Bean实例
     */
    public static void setBean(String className, Object obj) {
        BEAN_CONTAINER.put(className, obj);
    }
}
