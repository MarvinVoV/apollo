package sun.yamorn.blog.admin.cache.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import sun.yamorn.blog.admin.domain.auth.User;
import sun.yamorn.blog.framework.cache.util.JsonSerializeUtils;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * Created by root on 2015/11/12.
 */
@RunWith(JUnit4.class)
public class JsonUtilTest {
    @Test
    public void testDeserialize() throws IOException {
        String str = "{\"userId\":\"yamorn\",\"userName\":\"yamorn\",\"password\":\"admin\",\"createDate\":\"1990-05-05 11:10:10\",\"enable\":true,\"functions\":[{\"userId\":\"yamorn\",\"functionId\":1,\"functionName\":\"test_function\",\"functions\":[]}],\"roles\":[{\"roleId\":1,\"roleName\":\"ROLE_ADMIN\"}]}";

        User user = JsonSerializeUtils.toObject(str, User.class);
        assertNotNull(user);
    }

}
