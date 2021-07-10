package com.arlin.dao;

import com.arlin.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: StudentDao
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/23
 */
@Repository
public interface StudentDao extends BaseMapper<Student> {
}
