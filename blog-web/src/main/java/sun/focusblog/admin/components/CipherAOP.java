package sun.focusblog.admin.components;

import org.apache.commons.codec.binary.Base64;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import sun.focusblog.admin.annotation.Cipher;
import sun.focusblog.admin.annotation.CipherType;
import sun.focusblog.admin.domain.EncryptionType;
import sun.focusblog.framework.cache.util.AopUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 2015/12/14.
 * <p/>
 * Cipher AOP
 */
@Aspect
@Component
public class CipherAOP {

    @Pointcut("@annotation(sun.focusblog.admin.annotation.Cipher)")
    public void cipherAdvice() {
    }

    @Before("cipherAdvice()")
    public void cipherAction(JoinPoint pjp) throws Exception {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();

        assert method != null;
        Cipher cipher = method.getAnnotation(Cipher.class);
        String[] arguments = cipher.arguments();
        EncryptionType encryptionType = cipher.type();
        CipherType cipherType = cipher.cipherType();

        Object[] args = pjp.getArgs();

        String[] paramNameArray = AopUtils.getParametersName(method);

        Class<?>[] types = method.getParameterTypes();

        Map<String, Object> nameObjectMap = new HashMap<>();
        Map<String, Class<?>> nameClassMap = new HashMap<>();
        for (int i = 0; i < paramNameArray.length; i++) {
            nameObjectMap.put(paramNameArray[i], args[i]);
            nameClassMap.put(paramNameArray[i], types[i]);
        }

        for (String field : arguments) {
            String[] names = field.split("\\.");
            if (names.length == 1) {
                for (int i = 0; i < paramNameArray.length; i++) {
                    if (paramNameArray[i].equals(field)) {
                        if (args[i] != null) {
                            args[i] = process((String) args[i], encryptionType, cipherType);
                        }
                    }
                }
            } else {
                String objectName = names[0];
                String fieldName = names.length > 1 ? names[1] : field;
                Object obj = nameObjectMap.get(objectName);
                Class<?> type = nameClassMap.get(objectName);
                Field objectField = type.getDeclaredField(fieldName);
                objectField.setAccessible(true);
                Object val = objectField.get(obj);
                if (val == null) {
                    break;
                }
                String value = (String) val;
                switch (cipherType) {
                    case ENCRYPT:
                        objectField.set(obj, process(value, encryptionType, cipherType));
                        break;
                    case DECRYPT:
                        objectField.set(obj, process(value, encryptionType, cipherType));
                        break;
                }
            }
        }

    }

    /**
     * process method
     */
    private String process(String value, EncryptionType encryptionType, CipherType cipherType) {
        String encryptValue = null;
        switch (encryptionType) {
            case BASE64:
                switch (cipherType) {
                    case ENCRYPT:
                        encryptValue = new String(Base64.encodeBase64(value.getBytes()));
                        break;
                    case DECRYPT:
                        encryptValue = new String(Base64.decodeBase64(value.getBytes()));
                        break;
                }
                break;
            case MD5:
                // empty
                encryptValue = null;
                break;
        }
        return encryptValue;
    }

}
