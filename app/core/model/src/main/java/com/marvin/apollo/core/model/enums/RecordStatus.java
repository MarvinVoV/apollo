package com.marvin.apollo.core.model.enums;

import java.io.Serializable;

/**
 * @author hufeng
 * @version RecordStatus.java, v 0.1 2019-01-13 21:00 Exp $
 */

public enum RecordStatus implements Serializable {
    VALID(1, "正常"),
    INVALID(0, "无效");

    private int    code;
    private String desc;

    RecordStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static RecordStatus getByCode(int code) {
        for (RecordStatus item : RecordStatus.values()) {
            if (item.code == code) {
                return item;
            }
        }
        return null;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
