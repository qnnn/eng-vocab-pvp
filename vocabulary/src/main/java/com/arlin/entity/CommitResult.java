package com.arlin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: CommitResult
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommitResult {
    private Integer grade;
    private Integer gradeCount1;
    private Integer gradeCount2;
    private Integer gradeCount3;
    private Integer gradeCount4;
    private Integer gradeCount5;
}
