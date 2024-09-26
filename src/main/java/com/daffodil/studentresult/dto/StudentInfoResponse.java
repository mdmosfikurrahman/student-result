package com.daffodil.studentresult.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentInfoResponse {
    private String studentId;
    private String studentName;
    private String campusName;
    private String programName;
    private String semesterName;
}

