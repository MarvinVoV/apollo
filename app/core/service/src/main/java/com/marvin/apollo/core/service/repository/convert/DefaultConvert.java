package com.marvin.apollo.core.service.repository.convert;

import com.marvin.apollo.core.model.enums.InvisibleStatus;

/**
 * @author hufeng
 * @version DefaultConvert.java, v 0.1 2019-01-14 23:49 Exp $
 */

public interface DefaultConvert {

    default boolean intToBool(int value) {
        return value != 0;
    }

    default InvisibleStatus convertInvisibleStatus(int invisibleStatus) {
        for (InvisibleStatus item : InvisibleStatus.values()) {
            if (item.getCode() == invisibleStatus) {
                return item;
            }
        }
        return null;
    }
}
