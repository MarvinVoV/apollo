package sun.focusblog.framework.cache.annotation;

import java.lang.annotation.*;

/**
 * Created by yamorn on 2015/11/23.
 *
 * Cache synchronized
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheUpdate {
    /*
    A Logical partition namespace of cache.
 */
    String namespace() default "";

    /*
        A set of fields used to build the cache key.
     */
    String[] fieldsKey();

    /*
        The update entity of field
     */
    String valueField();

    /*
        The value of update entity
     */
    Class valueType();

    /*
        expire time. Units are seconds.
     */
    int expire() default -1;
}
