package cc.cleverfan.web.helper;

import cc.cleverfan.web.bean.BeanFactory;
import cc.cleverfan.web.utils.ArrayUtil;
import cc.cleverfan.web.annotation.Autowired;
import cc.cleverfan.web.bean.BeanContainer;
import cc.cleverfan.web.utils.CollectionUtil;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

/**
 * 依赖注入
 */
public final class IocHelper {

    static {
        Map<String, Object> beanContainer = BeanContainer.getBeanContainer();
        if (CollectionUtil.isNotEmpty(beanContainer)) {
            initIOC(beanContainer);
        }
    }

    private static void initIOC( Map<String, Object> beanContainer) {
        for (Map.Entry<String, Object> beanEntry : beanContainer.entrySet()) {
            String className = beanEntry.getKey();
            Object beanInstance = beanEntry.getValue();
            Class<?> beanClass = null;
            try {
                beanClass = Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //Controller类中定义的属性
            assert beanClass != null; // beanClass不能为空，加个断言
            Field[] beanFields = beanClass.getDeclaredFields();
            if (ArrayUtil.isNotEmpty(beanFields)) {

                Arrays.stream(beanFields)
                        .filter(beanField -> beanField.isAnnotationPresent(Autowired.class))
                        .forEach(beanField -> {
                            //成员变量的类
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanContainer.get(beanFieldClass.getName());
                            if (beanFieldInstance != null) {
                                //依赖注入
                                BeanFactory.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        });

            }
        }
    }
}
