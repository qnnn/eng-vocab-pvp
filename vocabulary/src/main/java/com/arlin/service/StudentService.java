package com.arlin.service;

import com.arlin.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @ClassName: StudentService
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/23
 */
public interface StudentService extends IService<Student> {

    Student login(Student student);
}
