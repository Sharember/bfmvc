package cc.cleverfan.aop.proxy;

/**
 * @author chengfan
 * @date 2017-10-5 22:38:36
 */
public interface Proxy {
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}