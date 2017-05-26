package cc.cleverfan.web.bean;

import java.lang.reflect.Method;

/**
 * 封装RequestMapping信息
 */
public class Handler {

    private Class<?> controllerClass;

    private Method method;

    public Handler(Class<?> controllerClass,Method method) {
        this.controllerClass = controllerClass;
        this.method = method;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getMethod() {
        return method;
    }
}
