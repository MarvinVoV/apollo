package com.amorn.convert;

import com.amorn.convert.entity.Attachment;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunyameng on 2014/5/12.
 */
public class StringToAttachmentList implements Converter<String,List<Attachment>>{
    @Override
    public List<Attachment> convert(String source) {
        if(source==null){
            return null;
        }
        List<Attachment> attachments=new ArrayList<>();
        for (String id : source.split(",")) {
            attachments.add(new Attachment("content-"+id));
        }
        return attachments;
    }
}
