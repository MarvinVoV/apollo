package com.yamorn.convert;

import com.yamorn.job.DemoTask;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by yamorn on 2015/7/14.
 */
public class JobToStringConvert implements Converter<DemoTask,String> {
    @Override
    public String convert(DemoTask source) {
        return source.toString();
    }
}
