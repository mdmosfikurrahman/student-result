package com.daffodil.studentresult.model;

import lombok.Data;

@Data
public class StudentInfo {
    private String studentId;
    private String fkCampus;
    private String campusName;
    private String studentName;
    private String batchId;
    private int batchNo;
    private int programCredit;
    private String programId;
    private String programName;
    private String progShortName;
    private String programType;
    private String deptShortName;
    private String departmentName;
    private String facultyName;
    private String facShortName;
    private String semesterId;
    private String semesterName;
    private String shift;
}
