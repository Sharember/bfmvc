package cc.cleverfan.web.helper;


import cc.cleverfan.web.annotation.RequestMapping;
import cc.cleverfan.web.bean.Handler;
import cc.cleverfan.web.bean.Request;
import cc.cleverfan.web.utils.ArrayUtil;
import cc.cleverfan.web.utils.CollectionUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chengfan
 */
public class ControllerHelper {

    //请求request与处理请求handler映射关系
    private static final Map<Request, Handler> RequestMap = new HashMap<>();

    static {
        ArrayList<Class<?>> controllerClasses = ClassHelper.getControllerClasses();
        if (CollectionUtil.isNotEmpty(controllerClasses)) {
            initRequestMapp(controllerClasses);
        }
    }


    private static void initRequestMapp(ArrayList<Class<?>> controllerClasses) {
        for (Class<?> controllerClass : controllerClasses) {
            Method[] methods = controllerClass.getDeclaredMethods();
            if (ArrayUtil.isNotEmpty(methods)) {

                Arrays.stream(methods)
                    .filter(method -> method.isAnnotationPresent(RequestMapping.class))
                    .forEach(method -> {
                        RequestMapping rm = method.getAnnotation(RequestMapping.class);
                        Request request = new Request(rm.method(), rm.path());
                        Handler handler = new Handler(controllerClass, method);
                        RequestMap.put(request, handler);
                    });
            }
        }
    }

    /**
     * 获取handler
     *
     * @param requestMethod
     * @param requestPath
     * @return
     */
    public static Handler getHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return RequestMap.get(request);
    }
}
