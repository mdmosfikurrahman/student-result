package com.daffodil.studentresult.service;

import com.daffodil.studentresult.model.StudentResponse;
import org.springframework.stereotype.Service;

@Service
public interface StudentResultService {
    StudentResponse getStudentInfo(String studentId);
}
