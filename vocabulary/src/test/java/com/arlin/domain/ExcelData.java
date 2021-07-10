package com.arlin.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

/**
 * @ClassName: ExcelData
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/30
 */
@Data
//@ContentRowHeight(20)
//@HeadRowHeight(20)
@ColumnWidth(15)
public class ExcelData {

    @ExcelProperty(value = "type")
    private int type;

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

    @ExcelProperty(value = "grade1")
    private int grade1;

    @ExcelProperty(value = "score1")
    private int score1;

    @ExcelProperty(value = "grade2")
    private int grade2;

    @ExcelProperty(value = "score2")
    private int score2;

    @ExcelProperty(value = "grade3")
    private int grade3;

    @ExcelProperty(value = "score3")
    private int score3;
}
