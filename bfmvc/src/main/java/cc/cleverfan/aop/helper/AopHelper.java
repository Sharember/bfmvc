package cc.cleverfan.aop.helper;

import cc.cleverfan.aop.annotation.Aspect;
import cc.cleverfan.aop.manager.ProxyManager;
import cc.cleverfan.aop.proxy.AbstractAspectProxy;
import cc.cleverfan.aop.proxy.Proxy;
import cc.cleverfan.web.bean.BeanContainer;
import cc.cleverfan.web.helper.ClassHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chengfan
 * @date 2017-10-6 23:50:59
 */
public class AopHelper {

    static {
        try {
            Map<Class<?>, List<Class<?>>> proxyMap = createProxyMap();
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
            for (Map.Entry<Class<?>, List<Proxy>> targetEntry : targetMap.entrySet()) {
                Class<?> targetClass = targetEntry.getKey();
                List<Proxy> proxyList = targetEntry.getValue();
                Object proxy = ProxyManager.createProxy(targetClass, proxyList);
                BeanContainer.setBean(targetClass.getName(), proxy);
            }
        } catch (Exception e) {
            //todo exception
        }
    }

    private static List<Class<?>> createTargetClassList(Aspect aspect) throws Exception {
        return ClassHelper.getClassListByAnnotation(aspect.value());
    }

    private static Map<Class<?>, List<Class<?>>> createProxyMap() throws Exception{
        Map<Class<?>, List<Class<?>>> proxyMap = new HashMap<>();
        List<Class<?>> proxyClassList = ClassHelper.getClassListBySuper(AbstractAspectProxy.class);
        for (Class<?> proxyClass : proxyClassList) {
            Aspect aspect = proxyClass.getAnnotation(Aspect.class);
            List<Class<?>> targetClassList = createTargetClassList(aspect);
            proxyMap.put(proxyClass, targetClassList);
        }
        return proxyMap;
    }

    private static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, List<Class<?>>> proxyMap) throws Exception{
        Map<Class<?>, List<Proxy>> targetMap = new HashMap<>();
        for (Map.Entry<Class<?>, List<Class<?>>> proxyEntry : proxyMap.entrySet()) {
            Class<?> proxyClass = proxyEntry.getKey();
            List<Class<?>> targetClassList = proxyEntry.getValue();
            for (Class<?> targetClass : targetClassList) {
                Proxy proxy = ((Proxy) proxyClass.newInstance());
                if (targetMap.containsKey(targetClass)) {
                    targetMap.get(targetClass).add(proxy);
                } else {
                    List<Proxy> proxyList = new ArrayList<>();
                    proxyList.add(proxy);
                    targetMap.put(targetClass, proxyList);
                }
            }
        }
        return targetMap;
    }
}