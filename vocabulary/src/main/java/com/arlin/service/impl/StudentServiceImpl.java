package com.arlin.service.impl;

import com.arlin.dao.StudentDao;
import com.arlin.entity.Student;
import com.arlin.service.StudentService;
import com.arlin.utils.MD5Util;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @ClassName: StudentServiceImpl
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/23
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {

    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public Student login(Student student) {
        return studentDao.selectOne(new LambdaQueryWrapper<Student>()
                .select(Student::getSno, Student::getSName)
                .eq(Student::getSno, student.getSno())
                .eq(Student::getPassword, MD5Util.code(student.getPassword())));
    }
}
