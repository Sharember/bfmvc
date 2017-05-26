package cc.cleverfan.web.helper;

import cc.cleverfan.web.annotation.Controller;
import cc.cleverfan.web.annotation.Service;
import cc.cleverfan.web.utils.ClassUtil;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * 类操作
 */
public class ClassHelper {

    //基础包名下所有的类
    private static ArrayList<Class<?>> classes;

    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        classes = ClassUtil.getClasses(basePackage);
    }

    /**
     * 获取基础包名下所有的类
     *
     * @return
     */
    public static ArrayList<Class<?>> getClasses() {
        return classes;
    }

    /**
     * 获取所有Service类
     *
     * @return
     */
    public static ArrayList<Class<?>> getServiceClasses() {
        return classes.stream().filter(c -> c.isAnnotationPresent(Service.class)).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * 获取所有Controller类
     *
     * @return
     */
    public static ArrayList<Class<?>> getControllerClasses() {
        return classes.stream().filter(c -> c.isAnnotationPresent(Controller.class)).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * 框架Bean容器主要管理Service,Controller类
     *
     * @return
     */
    public static ArrayList<Class<?>> getBeanClasses() {
        ArrayList<Class<?>> bc = new ArrayList<>();
        bc.addAll(getServiceClasses());
        bc.addAll(getControllerClasses());
        return bc;
    }
}
