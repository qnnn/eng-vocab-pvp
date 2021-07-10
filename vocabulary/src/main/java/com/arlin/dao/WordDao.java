package com.arlin.dao;

import com.arlin.entity.Word;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;


/**
 * @ClassName: WordDao
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/23
 */
@Repository
public interface WordDao extends BaseMapper<Word> {
}
