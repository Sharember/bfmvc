package cc.cleverfan.aop.annotation;

import java.lang.annotation.*;

/**
 * @author chengfan
 * @version $Id: Aspect.java, v 0.1 2017年10月05日 下午10:33 chengfan Exp $
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    /**
     * Value class.
     *
     * @return the class
     */
    Class<? extends Annotation> value();
}
