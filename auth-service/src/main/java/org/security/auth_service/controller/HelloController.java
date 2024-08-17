package org.security.auth_service.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.security.auth_service.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class HelloController {

List<Student> studentDB = new ArrayList<>(List.of(
        new Student(1,"Rahul",45),
        new Student(2,"Ambani",56)
)) ;

    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld(HttpServletRequest request) {
        return ResponseEntity.ok("Hello World " + request.getSession().getId());
    }

    @GetMapping("/students")
    public ResponseEntity<?> getStudentsInfo(){
        return ResponseEntity.status(HttpStatus.OK).body(studentDB) ;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/student")
    public ResponseEntity<?> addStudent(@RequestBody Student student) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(studentDB.add(student) + " Student added successfully") ;
    }
}
