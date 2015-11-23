package sun.focusblog.admin.annotation;

import java.lang.annotation.*;

/**
 * Created by root on 2015/11/23.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SessionUpdate {

    /**
     * session key
     */
    String key();

    /**
     * Argument field name
     */
    String field();

    /**
     * Argument field type
     */
    Class type();

}
