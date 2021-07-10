package com.arlin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: ScaleEnum
 * @Description: 各个等级数量
 * @Author: arlin
 * @Date: 2021/6/26
 */
@Getter
@AllArgsConstructor
public enum ScaleEnum {

    GRADE_1(1, new Integer[]{748, 748, 748, 748, 749}),
    GRADE_2(2, new Integer[]{1034, 1034, 1034, 1034, 1038}),
    GRADE_3(3, new Integer[]{938, 938, 938, 938, 938});

    private Integer type;
    private Integer[] nums;
}
