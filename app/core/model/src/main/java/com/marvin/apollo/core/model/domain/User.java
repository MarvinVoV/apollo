package com.marvin.apollo.core.model.domain;

import com.marvin.apollo.core.model.enums.Gender;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hufeng
 * @version User.java, v 0.1 2019-01-13 23:27 Exp $
 */
@Setter
@Getter
public class User {
    private Long         id;
    private String       account;
    private int          age;
    private Gender       gender;
}
