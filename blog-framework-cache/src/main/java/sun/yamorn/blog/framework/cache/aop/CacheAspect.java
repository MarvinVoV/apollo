package sun.yamorn.blog.framework.cache.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import sun.yamorn.blog.framework.cache.annotation.Cacheable;
import sun.yamorn.blog.framework.cache.redis.StringCacheStorage;

import java.lang.reflect.Method;

/**
 * Created by yamorn on 2015/11/10.
 *
 */

@Aspect
@Component
public class CacheAspect {
    private static final String NAMESPACE_SPLIT = "_";
    private static final String KEY_SPLIT = ":";

    @Autowired
    private StringCacheStorage cacheStorage;

    @Pointcut("@annotation(sun.yamorn.blog.framework.cache.annotation.Cacheable)")
    public void cacheAdvice() {
    }

    @Around("cacheAdvice()")
    public Object cache(ProceedingJoinPoint pjp) throws Throwable {
        Object retObj = null;

        Method method = getMethod(pjp);
        assert method != null;
        Cacheable cacheable = method.getAnnotation(sun.yamorn.blog.framework.cache.annotation.Cacheable.class);

        /**
         * The cacheKey is the full name of redis cache key
         */
        String cacheKey = parseKey(cacheable, method, pjp.getArgs());
        Class returnType = ((MethodSignature) pjp.getSignature()).getReturnType();

        retObj = cacheStorage.get(cacheKey, returnType);
        if(retObj == null){
            try{
                retObj = pjp.proceed();
                cacheStorage.set(cacheKey, retObj);
            }catch (Throwable e){
                e.printStackTrace();
            }
        }
        return retObj;
    }

    /**
     * Get the intercept method object.
     * <p/>
     * MethodSignature.getMethod() The top-level interface or parent class method objects
     * While the cache in the annotation method.
     * Should the object method uses reflection to obtain the current object so.
     */
    private Method getMethod(ProceedingJoinPoint pjp) {
        Object[] args = pjp.getArgs();
        Class[] argTypes = new Class[pjp.getArgs().length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }

        Method method = null;
        try {
            method = pjp.getTarget().getClass().getMethod(pjp.getSignature().getName(), argTypes);
        } catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        return method;
    }


    /**
     * Parse key and build a redis key with namespace.
     * The key's definition is support the SpEL Expression
     */
    private String parseKey(Cacheable cacheable, Method method, Object[] args) {
        StringBuilder sb = new StringBuilder();

        String namespace = cacheable.namespace();
        String[] fieldsKey = cacheable.fieldsKey();

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
        return sb.toString();
    }

    @AfterReturning(pointcut = "@annotation(sun.yamorn.blog.framework.cache.annotation.Cacheable)",
            returning = "retVal")
    public void doCache(Object retVal) {
        System.out.println("After Return");
    }

    @AfterThrowing(pointcut = "@annotation(sun.yamorn.blog.framework.cache.annotation.Cacheable)",
            throwing = "ex")
    public void doException(Exception ex) {

    }

}
