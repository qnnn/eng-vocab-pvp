package com.arlin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: LimitEnum
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/24
 */
@Getter
@AllArgsConstructor
public enum LimitEnum {

    Type_1(1, new Integer[]{1, 3741}),//3741 [748, 1496, 2244, 2992, 3741]
    Type_2(2, new Integer[]{3742, 8915}),//5174 [1034, 2069, 3104, 4139, 5174]
    Type_3(3, new Integer[]{8916, 13605});//4690 [938, 1876, 2814, 3752, 4690]

    private Integer type;
    private Integer[] limit;
}
