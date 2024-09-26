package com.daffodil.studentresult.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultInfoResponse {
    private String courseTitle;
    private double courseCredit;
    private double gradePoint;
    private String gradeLetter;
    private double totalCgpa;
}
