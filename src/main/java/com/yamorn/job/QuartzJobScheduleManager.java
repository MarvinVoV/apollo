package com.yamorn.job;

import com.yamorn.utils.ApplicationContextUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.Method;

/**
 * Created by yamorn on 2015/7/14.
 */
public class QuartzJobScheduleManager extends QuartzJobBean {
    private String targetObject;
    private String targetMethod;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try {
            Object target = ApplicationContextUtils.getBean(targetObject);

            Method m = null;
            try {
                m = target.getClass().getMethod(targetMethod, new Class[]{null});

                m.invoke(target, new Object[]{null});
            } catch (SecurityException | NoSuchMethodException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            throw new JobExecutionException(e);
        }

    }

    public void setTargetObject(String targetObject) {
        this.targetObject = targetObject;
    }

    public void setTargetMethod(String targetMethod) {
        this.targetMethod = targetMethod;
    }
}
