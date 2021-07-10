package com.arlin.service;

import com.arlin.dto.CommitDTO;
import com.arlin.dto.WordDTO;
import com.arlin.entity.Word;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.List;

/**
 * @ClassName: WordService
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/23
 */
public interface WordService extends IService<Word> {

    List<WordDTO> listWordByGrade(Integer type, Integer grade);

    ImmutablePair<List<WordDTO>, Integer> listWordByGrade(Long sno, CommitDTO commitDTO);

    Integer getScore(Long sno, CommitDTO commitDTO);

    void calculateGradeToRedis();
}
