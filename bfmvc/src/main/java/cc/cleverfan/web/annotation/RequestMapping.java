package cc.cleverfan.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 请求方法注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {

    enum RequetMethod {
        GET,
        POST,
        PUT,
        DELETE
    }

    //请求类型路径
    String path();
    //请求方法
    String method();
}
