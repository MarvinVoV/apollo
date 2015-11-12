package sun.focusblog.admin.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 2015/11/11.
 */
@RunWith(JUnit4.class)
public class ConvertTest {
    @Test
    public void testConvert() throws Exception {
        Object str = 10.0;
        List<String> list = Arrays.asList("h", "z");
        Map<String, String> map = new HashMap<>();
        map.put("a", "b");
        map.put("v", "c");

    }

    private boolean isSerializeToJson(Object object) {
        boolean result = false;
        if (!(object instanceof String) && !(object instanceof Number)) {
            result = true;
        }
        return result;
    }
}
