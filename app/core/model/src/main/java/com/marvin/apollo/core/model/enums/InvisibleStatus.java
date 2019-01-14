package com.marvin.apollo.core.model.enums;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author hufeng
 * @version InvisibleStatus.java, v 0.1 2019-01-13 21:04 Exp $
 */
@Getter
public enum InvisibleStatus implements Serializable {
    PUBLIC(1, "公开可见"),
    PRIVATE(2, "自己可见");


    private int    code;
    private String desc;

    InvisibleStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static InvisibleStatus getByCode(int code) {
        for (InvisibleStatus item : InvisibleStatus.values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }
}