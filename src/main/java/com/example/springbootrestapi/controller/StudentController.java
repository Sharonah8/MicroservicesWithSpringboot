package com.example.springbootrestapi.controller;

import com.example.springbootrestapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    // http://localhost:8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(1, "Sharonah", "Ken");
       // return new ResponseEntity<>(student, HttpStatus.OK);
        return ResponseEntity.ok()
                .header("custom-header", "Sharonah")
                .body(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Ann", "Njoki"));
        students.add(new Student(2, "mary", "Wanjiku"));
        students.add(new Student(3, "Lucy", "ann"));
        students.add(new Student(4, "Rajesh", "Koothrappali"));
        return ResponseEntity.ok(students);
    }

    // SpringBoot API with @Path variable
    // {id} - URI template variable
    // http://localhost:8080/students/1
    @GetMapping("{id}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId){
        Student student = new Student(studentId, "Sharonah", "Ken");
        return ResponseEntity.ok(student);
    }

    // to handle multiple path variables in a request url:
    @GetMapping("{id}/{first-name}/{last-name}")
    public Student studentPathVariable(@PathVariable("id") int studentId, @PathVariable("first-name") String firstName, @PathVariable("last-name") String lastName){
        return new Student(studentId, firstName,lastName);
    }

    // Spring boot REST API with request params
    // http://localhost:8080/students/query?id=1
    @GetMapping("query")
    public Student studentRequestVariable(@RequestParam int id){
        return new Student(id, "Hussein", "Hassan");
    }

    // REST API that handles multiple parameters in the request url
    // http:localhost:8080/students/many/query?id=1&firstName=Sharonah&lastName=Ken
    @GetMapping("many/query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id, @RequestParam String firstName, @RequestParam String lastName){
        Student student = new Student(id, firstName,lastName);
        return ResponseEntity.ok(student);
    }

    // Spring Boot REST API that handles HTTP POST request - creating new resource
    // @PostMapping and @RequestBody annotations
    // http://localhost:8080/students/create
    @PostMapping("create")
    // @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // Spring Boot REST API that handles HTTP PUT Request - update existing resource
    // http://localhost:8080/students/1/update
    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent( @RequestBody Student student, @PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    // Spring Boot REST API that handles HTTP DELETE request - delete existing resource
    // http://localhost:8080/students/3/delete
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return ResponseEntity.ok("Student deleted successfully!");
    }


}
