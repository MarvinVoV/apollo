package com.marvin.apollo.core.model.enums;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hufeng
 * @version InvisibleStatus.java, v 0.1 2019-01-13 21:04 Exp $
 */
@Getter
public enum InvisibleStatus implements Serializable {
    /**
     * 公开可见
     */
    PUBLIC(1, "公开可见"),
    /**
     * 自己可见
     */
    PRIVATE(2, "自己可见");


    private static final Map<Integer, InvisibleStatus> LOOKUP = new HashMap<>();
    private              int                           code;
    private              String                        desc;

    static {
        for (InvisibleStatus status : InvisibleStatus.values()) {
            LOOKUP.put(status.getCode(), status);
        }
    }

    InvisibleStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static InvisibleStatus getByCode(int code) {
        return LOOKUP.get(code);
    }
}