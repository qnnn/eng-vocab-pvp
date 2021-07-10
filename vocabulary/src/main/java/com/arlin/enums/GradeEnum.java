package com.arlin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: GradeEnum
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/24
 */
@Getter
@AllArgsConstructor
public enum GradeEnum {

    GRADE_1(1.0f, new Integer[]{30, 12, 4, 2, 2}),
    GRADE_2(1.025f, new Integer[]{20, 16, 8, 4, 2}),
    GRADE_3(1.05f, new Integer[]{10, 12, 16, 8, 4}),
    GRADE_4(1.075f, new Integer[]{6, 8, 14, 16, 6}),
    GRADE_5(1.1f, new Integer[]{4, 6, 10, 16, 14});

    private float percentage;
    private Integer[] nums;
}
