package com.example.exam_java_webapp.model.dao;

import com.example.exam_java_webapp.model.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class StudentDAO implements IStudentDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Student> getAll() {
        Session session = sessionFactory.openSession();
        try {
            List<Student> students;
            students = session.createQuery("from Student ", Student.class).list();
            return students;
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getCause());
        } finally {
            session.close();
        }
    }

    @Override
    public Boolean saveOrUpdate(Student student) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(student);
            session.getTransaction().commit();
            return true;
        } catch (Exception exception) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public Student findById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Student.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e.getCause());
        } finally {
            session.close();
        }
    }

    @Override
    public List<Student> findByName(String name) {
        Session session = sessionFactory.openSession();
        try {
            String sql = "SELECT C.student_name FROM Student C WHERE C.student_name LIKE :name";
            Query query = session.createQuery(sql);
            query.setParameter("name", "%" + name + "%");
            if (!query.getResultList().isEmpty()) {
                return (List<Student>) query.getResultList();
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e.getCause());
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(findById(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
