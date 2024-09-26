package com.daffodil.studentresult.service.impl;

import com.daffodil.studentresult.config.StudentApiConfig;
import com.daffodil.studentresult.dto.SemesterInfoResponse;
import com.daffodil.studentresult.dto.StudentInfoResponse;
import com.daffodil.studentresult.model.SemesterInfo;
import com.daffodil.studentresult.model.StudentInfo;
import com.daffodil.studentresult.service.StudentResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentResultServiceImpl implements StudentResultService {

    private final RestTemplate restTemplate;
    private final StudentApiConfig studentApiConfig;

    private String buildUrl(String baseUrl, String endPoint, String paramName, String paramValue) {
        return UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path(endPoint)
                .queryParam(paramName, paramValue)
                .toUriString();
    }

    private <T> T executeRequest(String url, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<T> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    responseType
            );
            log.info("RECEIVED RESPONSE SUCCESSFULLY");
            return responseEntity.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException exception) {
            log.error("Error details: ", exception);
            throw exception;
        }
    }

    @Override
    public StudentInfoResponse getStudentInfo(String studentId) {
        String baseUrl = studentApiConfig.getBaseUrl();
        String endPoint = studentApiConfig.getStudentInfoEndPoint();
        String url = buildUrl(baseUrl, endPoint, "studentId", studentId);
        StudentInfo studentInfo = executeRequest(url, StudentInfo.class);

        return buildStudentResponse(studentInfo);
    }

    @Override
    public SemesterInfoResponse getSemesterInfo() {
        String baseUrl = studentApiConfig.getBaseUrl();
        String semesterEndPoint = studentApiConfig.getSemesterInfoEndPoint();
        String url = baseUrl + semesterEndPoint;

        SemesterInfo[] semesters = executeRequest(url, SemesterInfo[].class);
        return buildSemesterResponse(semesters);
    }

    private SemesterInfoResponse buildSemesterResponse(SemesterInfo[] semesters) {
        return SemesterInfoResponse.builder()
                .semesters(List.of(semesters))
                .build();
    }


    private StudentInfoResponse buildStudentResponse(StudentInfo studentInfo) {
        return StudentInfoResponse.builder()
                .studentId(studentInfo.getStudentId())
                .studentName(studentInfo.getStudentName())
                .campusName(studentInfo.getCampusName())
                .programName(studentInfo.getProgramName())
                .semesterName(studentInfo.getSemesterName())
                .build();
    }
}
