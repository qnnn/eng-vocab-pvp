package com.arlin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: WordDTO
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordDTO {

    private String word;
    private Integer type;
    private Integer grade;
}
