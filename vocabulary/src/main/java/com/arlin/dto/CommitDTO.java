package com.arlin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Commit
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommitDTO {

    private Integer type;
    private Integer page;
    private Integer grade;

    private List<String> words;

    private List<WordDTO> wordDTOS = new ArrayList<>();

    public void add(WordDTO wordDTO) {
        wordDTOS.add(wordDTO);
    }
}
