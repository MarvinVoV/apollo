/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.marvin.apollo.core.model.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据状态，表示数据在DB的状态枚举
 *
 * @author hufeng
 * @version $Id: RecordStatus.java, v 0.1 2019年03月22日 22:07 hufeng Exp $
 */
public enum RecordStatus {
    /**
     * valid
     */
    VALID(1, "有效"),
    /**
     * invalid
     */
    INVALID(2, "无效");

    private static final Map<Integer, RecordStatus> LOOKUP = new HashMap<>();
    private              int                        code;
    private              String                     desc;

    static {
        Arrays.stream(RecordStatus.values()).forEach(item -> LOOKUP.put(item.getCode(), item));
    }

    RecordStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static RecordStatus getByCode(int code) {
        return LOOKUP.get(code);
    }

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public int getCode() {
        return code;
    }

    /**
     * Setter method for property <tt>code</tt>.
     *
     * @param code value to be assigned to property code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Getter method for property <tt>desc</tt>.
     *
     * @return property value of desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Setter method for property <tt>desc</tt>.
     *
     * @param desc value to be assigned to property desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }}