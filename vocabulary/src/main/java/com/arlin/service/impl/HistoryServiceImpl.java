package com.arlin.service.impl;

import com.arlin.dao.HistoryDao;
import com.arlin.entity.History;
import com.arlin.service.HistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @ClassName: HistoryServiceImpl
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/25
 */
@Service
public class HistoryServiceImpl extends ServiceImpl<HistoryDao, History> implements HistoryService {
}
