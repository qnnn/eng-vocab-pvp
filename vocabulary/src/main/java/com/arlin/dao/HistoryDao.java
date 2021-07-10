package com.arlin.dao;

import com.arlin.entity.History;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: HistoryDao
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/25
 */
@Repository
public interface HistoryDao extends BaseMapper<History> {
}
