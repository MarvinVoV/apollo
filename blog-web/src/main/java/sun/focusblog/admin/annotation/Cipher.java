package sun.focusblog.admin.annotation;

import sun.focusblog.admin.domain.EncryptionType;

import java.lang.annotation.*;

/**
 * Created by root on 2015/12/14.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cipher {
    /**
     * Encryption type
     *
     * @return type
     */
    EncryptionType type() default EncryptionType.BASE64;

    /**
     * Encryption arguments name.
     *
     * If argument is an object. field should be dotted-split string
     * such as article.title.
     *
     * @return argument's name
     */
    String[] arguments();

    /**
     * encrypt or decrypt
     *
     * @return type
     */
    CipherType cipherType() default CipherType.ENCRYPT;
}
