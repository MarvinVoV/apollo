package sun.focusblog.admin.components;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Created by root on 2015/11/18.
 */
@RunWith(JUnit4.class)
public class EmailClientTest {

    @Test
    public void testSendHtmlEmail() throws Exception {
        EmailClient client = new EmailClient();
        client.setHostname("smtp.163.com");
        client.setFrom("15136180579@163.com");
        client.setSenderName("focusblog");
        client.setUsername("15136180579");
        client.setPassword("ohgfsqtkhnnxtlft");
        String html = "<div style=\"fond-size:16px;\"><a href=\"javascript:void(0)\"> hello world</a></div>";
        client.sendHtmlEmail("1015687411@qq.com","html mail", html);
    }
}