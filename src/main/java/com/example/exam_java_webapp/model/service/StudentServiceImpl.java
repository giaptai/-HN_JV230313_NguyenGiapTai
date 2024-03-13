package com.example.exam_java_webapp.model.service;

import com.example.exam_java_webapp.model.dao.IStudentDAO;
import com.example.exam_java_webapp.model.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {
    private IStudentDAO studentDAO;

    public StudentServiceImpl(IStudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public List<Student> getAll() {
        return studentDAO.getAll();
    }

    @Override
    public Boolean saveOrUpdate(Student student) {
        return studentDAO.saveOrUpdate(student);
    }

    @Override
    public Student findById(Integer id) {
        return studentDAO.findById(id);
    }

    @Override
    public List<Student> findByName(String name) {
        return studentDAO.findByName(name);
    }

    @Override
    public void delete(Integer id) {
        studentDAO.delete(id);
    }
}
