package com.daffodil.studentresult.service;

import com.daffodil.studentresult.dto.response.ResultInfoResponse;
import com.daffodil.studentresult.dto.response.SemesterInfoResponse;
import com.daffodil.studentresult.dto.response.StudentInfoResponse;

import java.util.List;

public interface StudentResultService {
    StudentInfoResponse getStudentInfo(String studentId);
    List<SemesterInfoResponse> getSemesterInfo();
    List<ResultInfoResponse> getStudentResults(String studentId, String semesterId);
}
