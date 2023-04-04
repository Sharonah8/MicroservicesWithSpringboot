package com.example.springbootrestapi.controller;

import com.example.springbootrestapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    // http://localhost:8080/student
    @GetMapping("student")
    public Student getStudent(){
        Student student = new Student(1, "Sharonah", "Ken");
        return student;
    }

    @GetMapping("students")
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Ann", "Njoki"));
        students.add(new Student(2, "mary", "Wanjiku"));
        students.add(new Student(3, "Lucy", "ann"));
        students.add(new Student(4, "Rajesh", "Koothrappali"));
        return students;
    }

    // SpringBoot API with @Path variable
    // {id} - URI template variable
    // http://localhost:8080/students/1
    @GetMapping("students/{id}")
    public Student studentPathVariable(@PathVariable("id") int studentId){
        return new Student(studentId, "Sharonah", "Ken");
    }

    // to handle multiple path variables in a request url:
    @GetMapping("students/{id}/{first-name}/{last-name}")
    public Student studentPathVariable(@PathVariable("id") int studentId, @PathVariable("first-name") String firstName, @PathVariable("last-name") String lastName){
        return new Student(studentId, firstName,lastName);
    }

    // Spring boot REST API with request params
    // http://localhost:8080/students/query?id=1
    @GetMapping("students/query")
    public Student studentRequestVariable(@RequestParam int id){
        return new Student(id, "Hussein", "Hassan");
    }

    // REST API that handles multiple parameters in the request url
    // http:localhost:8080/students/many/query?id=1&firstName=Sharonah&lastName=Ken
    @GetMapping("students/many/query")
    public Student studentRequestVariable(@RequestParam int id, @RequestParam String firstName, @RequestParam String lastName){
        return new Student(id, firstName,lastName);
    }

    // Spring Boot REST API that handles HTTP POST request
    // @PostMapping and @RequestBody annotations
    // http://localhost:8080/students/create
    @PostMapping("/students/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }
}
