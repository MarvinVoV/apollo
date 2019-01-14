package com.marvin.apollo.core.service.repository.convert;

import com.marvin.apollo.core.model.enums.Gender;
import com.marvin.apollo.core.model.enums.InvisibleStatus;
import com.marvin.apollo.core.model.enums.RecordStatus;

/**
 * @author hufeng
 * @version DefaultConvert.java, v 0.1 2019-01-14 23:49 Exp $
 */

public interface DefaultConvert {

    default RecordStatus convertRecordStatus(int status) {
        for (RecordStatus item : RecordStatus.values()) {
            if (item.getCode() == status) {
                return item;
            }
        }
        return null;
    }

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

    default Gender convertIntToGender(int value) {
        for (Gender item : Gender.values()) {
            if (item.getCode() == value) {
                return item;
            }
        }
        return null;
    }
}
