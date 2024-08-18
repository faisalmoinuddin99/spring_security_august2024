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

    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld(HttpServletRequest request) {
        return ResponseEntity.ok("Hello World " + request.getSession().getId());
    }

}
