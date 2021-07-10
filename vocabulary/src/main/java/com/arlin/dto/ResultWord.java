package com.arlin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName: ResultWord
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultWord {

    private List<String> words;
    private Integer grade;
}
