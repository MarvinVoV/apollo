package sun.yamorn.blog.admin.cache.core;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import sun.yamorn.blog.admin.cache.annotation.Cacheable;

import java.lang.reflect.Method;

/**
 * Created by root on 2015/11/10.
 */
@Aspect
@Component
public class CacheAspect {

    @Pointcut("@annotation(sun.yamorn.blog.admin.cache.annotation.Cacheable)")
    public void cacheAdvice() {
    }

    @Around("cacheAdvice()")
    public Object cache(ProceedingJoinPoint pjp) throws Throwable {
        Object retObj = null;

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
        assert method != null;
        Cacheable cacheable = method.getAnnotation(sun.yamorn.blog.admin.cache.annotation.Cacheable.class);

        String[] fieldsKey = cacheable.fieldsKey();

        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNameArray = u.getParameterNames(method);

        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        for(int i=0;i<paramNameArray.length;i++){
            context.setVariable(paramNameArray[i], args[i]);
        }

        for(String key : fieldsKey){
            String val = parser.parseExpression(key).getValue(context,String.class);
            System.out.println(val);
        }


        return retObj;
    }

    @AfterReturning(pointcut = "@annotation(sun.yamorn.blog.admin.cache.annotation.Cacheable)",
            returning = "retVal")
    public void doCache(Object retVal) {
        System.out.println("After Return");
    }

    @AfterThrowing(pointcut = "@annotation(sun.yamorn.blog.admin.cache.annotation.Cacheable)",
            throwing = "ex")
    public void doException(Exception ex) {

    }

}
