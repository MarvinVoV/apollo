package sun.focusblog.framework.cache.demo;

import org.springframework.stereotype.Component;
import sun.focusblog.framework.cache.annotation.Cacheable;

/**
 * Created by root on 2015/11/11.
 */
@Component
public class Hello implements IHello{

    @Cacheable(fieldsKey = {"#username", "#password"})
    public String query(String username, String password) {
        return "test-key";
    }
}
