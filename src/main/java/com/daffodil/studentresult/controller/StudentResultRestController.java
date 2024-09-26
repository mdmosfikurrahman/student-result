package com.daffodil.studentresult.controller;

import com.daffodil.studentresult.dto.SemesterInfoResponse;
import com.daffodil.studentresult.dto.StudentInfoResponse;
import com.daffodil.studentresult.service.StudentResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentResultRestController {

    private final StudentResultService studentResultService;

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentInfoResponse> getStudentInfo(@PathVariable String studentId) {
        try {
            StudentInfoResponse response = studentResultService.getStudentInfo(studentId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping()
    public ResponseEntity<List<SemesterInfoResponse>> getSemesterInfo() {
        try {
            List<SemesterInfoResponse> response = studentResultService.getSemesterInfo();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
