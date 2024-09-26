package com.daffodil.studentresult.service;

import com.daffodil.studentresult.dto.SemesterInfoResponse;
import com.daffodil.studentresult.dto.StudentInfoResponse;

import java.util.List;

public interface StudentResultService {
    StudentInfoResponse getStudentInfo(String studentId);
    List<SemesterInfoResponse> getSemesterInfo();
}
