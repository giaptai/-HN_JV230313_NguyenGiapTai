package com.example.exam_java_webapp.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int student_id;
    @Column(name = "address")
    private String address;
    @Column(name = "birthday")
    private LocalDate birthday;
    @Column(name = "image_url")
    private String image_url;
    @Column(name = "phone_number")
    private String phone_number;
    @Column(name = "sex")
    private boolean sex;
    @Column(name = "student_name")
    private String student_name;

    public Student() {
    }

    public Student(int student_id, String address, LocalDate birthday, String image_url, String phone_number, boolean sex, String student_name) {
        this.student_id = student_id;
        this.address = address;
        this.birthday = birthday;
        this.image_url = image_url;
        this.phone_number = phone_number;
        this.sex = sex;
        this.student_name = student_name;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }
}
