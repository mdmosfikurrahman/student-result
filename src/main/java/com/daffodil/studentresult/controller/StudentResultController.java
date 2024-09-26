package com.daffodil.studentresult.controller;

import com.daffodil.studentresult.model.StudentInfo;
import com.daffodil.studentresult.model.StudentResponse;
import com.daffodil.studentresult.service.StudentResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentResultController {

    private final StudentResultService studentResultService;

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentResponse> getStudentInfo(@PathVariable String studentId) {
        try {
            StudentResponse response = studentResultService.getStudentInfo(studentId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
