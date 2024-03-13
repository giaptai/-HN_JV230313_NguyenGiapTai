package com.example.exam_java_webapp.model.service;

import com.example.exam_java_webapp.model.entity.Student;

import java.util.List;

public interface IStudentService {
    List<Student> getAll();

    Boolean saveOrUpdate(Student student);

    Student findById(Integer id);
    List<Student> findByName(String name);

    void delete(Integer id);
}
