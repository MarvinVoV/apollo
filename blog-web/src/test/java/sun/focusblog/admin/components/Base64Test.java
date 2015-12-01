package sun.focusblog.admin.components;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.util.Base64Utils;

import java.util.UUID;

/**
 * Created by root on 2015/11/23.
 */
@RunWith(JUnit4.class)
public class Base64Test {
    @Test
    public void testEncode() {
        String str = "admin";
        String encodeString = Base64Utils.encodeToString(str.getBytes());
        System.out.println(encodeString);
    }

    @Test
    public void testDecode() {
        String str = "MTIzNDU2";
        byte[] bytes = Base64Utils.decodeFromString(str);
        String decodeStr = new String(bytes);
        System.out.println(decodeStr);
    }

    @Test
    public void testUUID() {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        System.out.println(uuid.length());
    }

}
