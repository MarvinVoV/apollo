package sun.focusblog.framework.cache.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import sun.focusblog.framework.cache.annotation.CacheUpdate;
import sun.focusblog.framework.cache.redis.StringCacheStorage;
import sun.focusblog.framework.cache.annotation.Cacheable;
import sun.focusblog.framework.cache.util.AopUtils;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by yamorn on 2015/11/10.
 * <p/>
 * Cache aspect used to intercept method which has @Cacheable annotation on it.
 * Then do the cache job.
 * Note: methods which are intercepted must not have primitive type arguments.
 */

@Aspect
@Component
public class CacheAspect {
    private static final String NAMESPACE_SPLIT = "_";
    private static final String KEY_SPLIT = ":";

    private static final Logger logger = Logger.getLogger(CacheAspect.class.getName());

    @Autowired
    private StringCacheStorage cacheStorage;

    //========================================================================
    //                          Cache storage AOP
    //========================================================================

    @Pointcut("@annotation(sun.focusblog.framework.cache.annotation.Cacheable)")
    public void cacheAdvice() {
    }

    @Around("cacheAdvice()")
    public Object cache(ProceedingJoinPoint pjp) throws Throwable {
        Object retObj;

        Method method = AopUtils.getMethod(pjp);
        assert method != null;
        Cacheable cacheable = method.getAnnotation(Cacheable.class);

        /**
         * The cacheKey is the full name of redis cache key
         */
        String namespace = cacheable.namespace();
        String[] fieldsKey = cacheable.fieldsKey();
        String cacheKey = parseKey(namespace, fieldsKey, method, pjp.getArgs());
        Class returnType = ((MethodSignature) pjp.getSignature()).getReturnType();

        retObj = cacheStorage.get(cacheKey, returnType);
        if (retObj == null) {
            try {
                retObj = pjp.proceed();
                int expire = cacheable.expire();
                if (expire > 0) {
                    cacheStorage.setEx(cacheKey, retObj, expire);
                } else {
                    cacheStorage.set(cacheKey, retObj);
                }
            } catch (Throwable e) {
                logger.log(Level.SEVERE, e.getLocalizedMessage());
            }
        }
        return retObj;
    }


    /**
     * Parse key and build a redis key with namespace.
     * The key's definition is support the SpEL Expression
     */
    private String parseKey(String namespace, String[] fieldsKey, Method method, Object[] args) {
        StringBuilder sb = new StringBuilder();
        /**
         * Get method parameters using the spring support library.
         */
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNameArray = u.getParameterNames(method);
        /**
         * Put all the parameters into SpEL context and analysis key using SpEL
         */
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < paramNameArray.length; i++) {
            context.setVariable(paramNameArray[i], args[i]);
        }

        sb.append(namespace).append(NAMESPACE_SPLIT);
        for (String key : fieldsKey) {
            String value = parser.parseExpression(key).getValue(context, String.class);
            sb.append(value).append(KEY_SPLIT);
        }
        String fullKey = sb.toString();
        int index;
        if (fullKey.length() > 0 && (index = fullKey.lastIndexOf(":")) > 0) {
            fullKey = fullKey.substring(0, index);
        }
        return fullKey;
    }

    @AfterThrowing(pointcut = "@annotation(sun.focusblog.framework.cache.annotation.Cacheable)",
            throwing = "ex")
    public void doException(Exception ex) {
        logger.log(Level.SEVERE, ex.getLocalizedMessage());
    }


    //========================================================================
    //                          Cache Update AOP
    //========================================================================

    @Pointcut("@annotation(sun.focusblog.framework.cache.annotation.CacheUpdate)")
    public void cacheUpdateAdvice() {
    }

    @AfterReturning(pointcut = "cacheUpdateAdvice()", returning = "rtv")
    public void cacheUpdate(JoinPoint jp, Object rtv) {
        Method method = AopUtils.getMethod(jp);
        assert method != null;

        CacheUpdate cacheUpdate = method.getAnnotation(CacheUpdate.class);
        /**
         * The cacheKey is the full name of redis cache key
         */
        String namespace = cacheUpdate.namespace();
        String[] fieldsKey = cacheUpdate.fieldsKey();
        String cacheKey = parseKey(namespace, fieldsKey, method, jp.getArgs());

        Class type = cacheUpdate.valueType();
        int expire = cacheUpdate.expire();
        boolean updateRetVal = cacheUpdate.updateRetVal();

        Object value;
        if (updateRetVal) {
            value = rtv;
        } else {
            String valueField = cacheUpdate.valueField();
            value = getUpdateFieldValue(valueField, method, jp, type);
        }

        if (expire > 0) {
            cacheStorage.setEx(cacheKey, value, expire);
        } else {
            cacheStorage.set(cacheKey, value);
        }
    }

    private Object getUpdateFieldValue(String valueField, Method method, JoinPoint jp, Class valueType) {
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNameArray = u.getParameterNames(method);
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < paramNameArray.length; i++) {
            context.setVariable(paramNameArray[i], jp.getArgs()[i]);
        }
        return parser.parseExpression(valueField).getValue(context, valueType);
    }

}
