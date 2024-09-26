package com.daffodil.studentresult.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentResponse {
    private String studentId;
    private String studentName;
    private String campusName;
    private String programName;
    private String semesterName;
}

