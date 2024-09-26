package com.daffodil.studentresult.controller;

import com.daffodil.studentresult.dto.request.StudentInfoRequest;
import com.daffodil.studentresult.dto.request.StudentResultRequest;
import com.daffodil.studentresult.dto.response.ResultInfoResponse;
import com.daffodil.studentresult.dto.response.SemesterInfoResponse;
import com.daffodil.studentresult.dto.response.StudentInfoResponse;
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

    @PostMapping("/info")
    public ResponseEntity<StudentInfoResponse> getStudentInfo(@RequestBody StudentInfoRequest request) {
        try {
            StudentInfoResponse response = studentResultService.getStudentInfo(request.getStudentId());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/semesters")
    public ResponseEntity<List<SemesterInfoResponse>> getSemesterInfo() {
        try {
            List<SemesterInfoResponse> response = studentResultService.getSemesterInfo();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/semester-results")
    public ResponseEntity<List<ResultInfoResponse>> getStudentResults(
            @RequestBody StudentResultRequest request) {
        try {
            List<ResultInfoResponse> results = studentResultService.getStudentResults(request.getStudentId(), request.getSemesterId());
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
