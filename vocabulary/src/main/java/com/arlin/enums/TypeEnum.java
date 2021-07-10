package com.arlin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: TypeEnum
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/24
 */
@Getter
@AllArgsConstructor
public enum TypeEnum {

    Type_1(1, "四级词汇"),
    Type_2(2, "六级词汇"),
    Type_3(3, "考研词汇");

    private Integer type;
    private String name;
}
