package sun.yamorn.blog.framework.cache.annotation;

import java.lang.annotation.*;

/**
 * Created by yamorn on 2015/11/10.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cacheable {
    String namespace() default "";
    String[] fieldsKey();
    int expire() default -1;
}
