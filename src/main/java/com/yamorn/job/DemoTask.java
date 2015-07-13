package com.yamorn.job;

import org.springframework.format.support.FormattingConversionServiceFactoryBean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yamorn on 2015/7/14.
 */
public class DemoTask implements Serializable{
    public void test(){
        System.out.println("===" + new Date().toLocaleString());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
