package cc.cleverfan.aop.manager;

import cc.cleverfan.aop.proxy.Proxy;
import cc.cleverfan.aop.proxy.ProxyChain;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.util.List;

/**
 * @author chengfan
 * @date 2017-10-5 22:56:12
 */
public class ProxyManager {
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(final Class<?> targetClass, final List<Proxy> proxyList){
        return (T) Enhancer.create(targetClass,
                (MethodInterceptor) (targetObject, targetMethod, methodParams, methodProxy) ->
                        new ProxyChain(targetClass, targetObject, targetMethod, methodProxy, methodParams, proxyList));
    }
}