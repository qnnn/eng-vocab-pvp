package com.arlin.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

/**
 * @ClassName: ExcelRandom
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/30
 */
@Data
@ColumnWidth(15)
public class ExcelRandom {

    @ExcelProperty(value = "ratio1")
    private double ratio1;

    @ExcelProperty(value = "ratio2")
    private double ratio2;

    @ExcelProperty(value = "ratio3")
    private double ratio3;

    @ExcelProperty(value = "ratio4")
    private double ratio4;

    @ExcelProperty(value = "ratio5")
    private double ratio5;

    @ExcelProperty(value = "score")
    private int score;
}
