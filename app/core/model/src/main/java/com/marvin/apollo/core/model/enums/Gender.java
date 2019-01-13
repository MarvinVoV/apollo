package com.marvin.apollo.core.model.enums;

/**
 * @author hufeng
 * @version Gender.java, v 0.1 2019-01-13 23:51 Exp $
 */

public enum Gender {
    MALE(1, "男"),
    FAMALE(2, "女"),
    ;

    private int    code;
    private String desc;

    Gender(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static Gender getByCode(int code) {
        for (Gender item : values()) {
            if (item.code == code) {
                return item;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }}
