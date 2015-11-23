package sun.focusblog.admin.components;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.focusblog.admin.annotation.SessionUpdate;
import sun.focusblog.framework.cache.util.AopUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Created by root on 2015/11/23.
 *
 * Synchronized session content.
 *
 * Note: method which updates session attribute must only have one parameter
 *       and must have return type.
 */
@Component
@Aspect
public class SessionAOP {
    @Pointcut("@annotation(sun.focusblog.admin.annotation.SessionUpdate)")
    public void sessionAdvice() {
    }

    @AfterReturning(pointcut = "sessionAdvice()", returning = "rtv")
    public void updateSession(JoinPoint pjp, Object rtv) {
        Method method = AopUtils.getMethod(pjp);
        assert method != null;
        SessionUpdate sessionUpdate = method.getAnnotation(SessionUpdate.class);

        String key = sessionUpdate.key();
        Object value = getSessionValue(sessionUpdate, method, pjp);

        /**
         * Get session from out of container
         */
        HttpServletRequest curRequest =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();
        curRequest.setAttribute(key, value);
    }

    private Object getSessionValue(SessionUpdate sessionUpdate,Method method, JoinPoint pjp) {
        String field = sessionUpdate.field();
        Class type = sessionUpdate.type();

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
            context.setVariable(paramNameArray[i], pjp.getArgs()[i]);
        }
        Object value = parser.parseExpression(field).getValue(context, type);
        assert value != null;
        return value;
    }


}
