package com.daffodil.studentresult.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SemesterInfoResponse {
    private String semesterId;
    private int semesterYear;
    private String semesterName;
}
