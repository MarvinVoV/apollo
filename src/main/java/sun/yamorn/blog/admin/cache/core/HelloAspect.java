package sun.yamorn.blog.admin.cache.core;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by 264929 on 2015/11/10.
 */

@Aspect
@Component
public class HelloAspect {
    private static final Logger logger = LoggerFactory.getLogger(HelloAspect.class);

    @Pointcut("execution(* sun.yamorn.blog.admin.dao.IUserDao.query(..))")
    public void queryUserPointCut(){}

    @Before("queryUserPointCut()")
    public void beforeQueryUserAdvice(){
        System.out.println("-------------------");
        logger.info("before====================");
    }
}
