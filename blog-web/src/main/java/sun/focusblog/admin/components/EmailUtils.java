package sun.focusblog.admin.components;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

/**
 * Created by root on 2015/11/17.
 */
public class EmailUtils {
    private String hostname;
    private String charset;
    private String from;
    private String to;
    private String username;
    private String password;
    private String senderName;

    public static void main(String[] args) throws Exception {
        Email email = new SimpleEmail();
        email.setHostName("smtp.163.com");
        email.setCharset("utf-8");


        email.setFrom("15136180579@163.com", "focusblog", "utf-8");// 设置发件人信息
        email.setAuthenticator(new DefaultAuthenticator("15136180579", "ohgfsqtkhnnxtlft"));
        email.setSSLOnConnect(true);
        email.setSubject("TestMail");
        email.setMsg("This is a test mail ... :-)");
        email.addTo("444352750@qq.com");
        email.send();
    }


    public void sendSimpleTextEmail(){

    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
