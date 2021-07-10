package com.arlin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: WordEnum
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/23
 */
@Getter
@AllArgsConstructor
public enum WordEnum {

    /**
     * 四级词汇
     */
    CET4(1, "word_cet4"),

    /**
     * 六级词汇
     */
    CET6(2, "word_cet6"),

    /**
     * 考研词汇
     */
    KY(3, "word_ky");

    private final Integer type;

    private final String name;
}
