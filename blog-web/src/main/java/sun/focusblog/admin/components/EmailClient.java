package sun.focusblog.admin.components;

import org.apache.commons.mail.*;

/**
 * Created by root on 2015/11/17.
 * <p/>
 * apache email utility
 */
public class EmailClient {
    private String hostname;
    private String charset = "utf-8";
    private String from;
    private String username;
    private String password;
    private String senderName;
    private String smtpPort = "25";
    private int socketTimeout = 60000; // default 60 seconds


    /**
     * Send simple text-type email
     *
     * @param to      receiver
     * @param subject email subject
     * @param message email content
     */
    public void sendSimpleTextEmail(String to, String subject, String message) {
        Email email = new SimpleEmail();
        try {
            build(email, to, subject)
                    .setMsg(message)
                    .send();
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Send html-type email
     *
     * @param to      receiver
     * @param subject email subject
     * @param html    email content
     */
    public void sendHtmlEmail(String to, String subject, String html) {
        HtmlEmail email = new HtmlEmail();
        try {
            build(email, to, subject)
                    .setHtmlMsg(html)
                    .setTextMsg("Your email client does not support HTML messages")
                    .send();
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Build basic property of email object
     * @param t
     * @param to        receiver
     * @param subject   subject
     * @param <T>       Email implementation
     * @return          email object
     */
    private <T extends Email> T build(T t, String to, String subject) {
        try {
            t.setHostName(this.hostname);
            t.setCharset(this.charset);
            t.setFrom(this.from, this.senderName, this.charset);
            t.setAuthenticator(new DefaultAuthenticator(this.username, this.password));
            t.setSSLOnConnect(true);
            t.setSmtpPort(Integer.valueOf(this.smtpPort));
            t.setSubject(subject);
            t.addTo(to);
            t.setSocketTimeout(this.socketTimeout);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return t;
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

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }
}
