package com.yamorn.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by yamorn on 2015/7/14.
 */
public class RunMeJob extends QuartzJobBean {
    private DemoTask demoTask;

    public void setDemoTask(DemoTask demoTask) {
        this.demoTask = demoTask;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        demoTask.test();
    }
}
