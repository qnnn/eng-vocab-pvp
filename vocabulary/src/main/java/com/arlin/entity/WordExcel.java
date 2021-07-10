package com.arlin.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @ClassName: WordExcel
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/23
 */
@Data
public class WordExcel {

    @ExcelProperty(value = "Rank")
    private String rank;

    @ExcelProperty(value = "Word")
    private String word;

    @ExcelProperty(value = "Collins")
    private String collins;

    @ExcelProperty(value = "Definition")
    private String definition;
}
