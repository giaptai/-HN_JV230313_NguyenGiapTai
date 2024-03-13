package com.example.exam_java_webapp.controller;

import com.example.exam_java_webapp.model.entity.Student;
import com.example.exam_java_webapp.model.service.IStudentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Controller
@PropertySource("classpath:application.properties")
public class StudentController {
    private IStudentService studentService;

    @Value("${path}")
    private String path;

    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student")
    public String index(
            Model model,
            @RequestParam(name = "name", defaultValue = "", required = false) String name
    ) {
        System.out.println(name);
        List<Student> students = new ArrayList<>();
        if (name.isEmpty()) {
            students = studentService.getAll();
        } else {
            students = studentService.findByName(name);
        }
        model.addAttribute("students", students);
        model.addAttribute("searchName", name);
        model.addAttribute("student", new Student());
        return "student/index";
    }

    @PostMapping("/student")
    public String create(
            @ModelAttribute("student") Student student,
            @RequestParam("fileImage") MultipartFile file,
            Model model) {
        // upload file
        String fileName = file.getOriginalFilename();
        File destination = new File(path + "/" + fileName);
        try {
            Files.write(destination.toPath(), file.getBytes(), StandardOpenOption.CREATE);
            student.setImage_url(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        studentService.saveOrUpdate(student);
        return "redirect:/student";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "student/edit";
    }

    //edit that
    @PostMapping("/edit/{id}")
    public String editStudent(
            @PathVariable(name = "id") int id,
            @ModelAttribute("student") Student student,
            @RequestParam("fileImage") MultipartFile file) {
        // upload file
        String fileName = file.getOriginalFilename();
        File destination = new File(path + "/" + fileName);
        try {
            Files.write(destination.toPath(), file.getBytes(), StandardOpenOption.CREATE);
            student.setImage_url(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        student.setStudent_id(id);
        studentService.saveOrUpdate(student);
        return "redirect:/edit/" + id;
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        System.out.println(id);
        studentService.delete(id);
        return "redirect:/student";
    }
}
