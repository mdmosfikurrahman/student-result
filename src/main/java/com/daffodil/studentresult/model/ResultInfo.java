package com.daffodil.studentresult.model;

import lombok.Data;

@Data
public class ResultInfo {
    private String semesterId;
    private String semesterName;
    private int semesterYear;
    private String studentId;
    private String courseId;
    private String customCourseId;
    private String courseTitle;
    private double totalCredit;
    private Double grandTotal;
    private double pointEquivalent;
    private String gradeLetter;
    private double cgpa;
    private String blocked;
    private String blockCause;
    private String tevalSubmitted;
    private String teval;
    private String semesterAccountsClearance;
}

