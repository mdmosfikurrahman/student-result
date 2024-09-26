package com.daffodil.studentresult.service.impl;

import com.daffodil.studentresult.config.StudentApiConfig;
import com.daffodil.studentresult.dto.response.ResultInfoResponse;
import com.daffodil.studentresult.dto.response.SemesterInfoResponse;
import com.daffodil.studentresult.dto.response.StudentInfoResponse;
import com.daffodil.studentresult.exception.StudentApiException;
import com.daffodil.studentresult.model.ResultInfo;
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

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentResultServiceImpl implements StudentResultService {

    private final RestTemplate restTemplate;
    private final StudentApiConfig studentApiConfig;

    private String buildUrl(String baseUrl, String endPoint, Map<String, String> queryParams) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path(endPoint);
        queryParams.forEach(uriBuilder::queryParam);
        return uriBuilder.toUriString();
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private <T> T executeRequest(String url, Class<T> responseType) {
        HttpEntity<String> entity = new HttpEntity<>(createHeaders());

        try {
            ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, responseType);
            return Objects.requireNonNull(responseEntity.getBody(), "No response received from API");
        } catch (HttpClientErrorException | HttpServerErrorException exception) {
            log.error("Error details: ", exception);
            throw new StudentApiException("API Error: " + exception.getMessage(), exception, exception.getStatusCode().value());
        }
    }

    @Override
    public StudentInfoResponse getStudentInfo(String studentId) {
        String url = buildUrl(studentApiConfig.getBaseUrl(), studentApiConfig.getStudentInfoEndPoint(), Map.of("studentId", studentId));
        StudentInfo studentInfo = executeRequest(url, StudentInfo.class);
        return buildStudentResponse(studentInfo);
    }

    @Override
    public List<SemesterInfoResponse> getSemesterInfo() {
        String url = buildUrl(studentApiConfig.getBaseUrl(), studentApiConfig.getSemesterInfoEndPoint(), Map.of());
        SemesterInfo[] semesters = executeRequest(url, SemesterInfo[].class);
        return buildSemesterResponseList(semesters);
    }

    @Override
    public List<ResultInfoResponse> getStudentResults(String studentId, String semesterId) {
        String url = buildUrl(studentApiConfig.getBaseUrl(), studentApiConfig.getStudentResultEndPoint(), Map.of("studentId", studentId, "semesterId", semesterId));
        ResultInfo[] results = executeRequest(url, ResultInfo[].class);
        return buildResultResponseList(results);
    }

    private List<ResultInfoResponse> buildResultResponseList(ResultInfo[] resultInfos) {
        return Arrays.stream(resultInfos)
                .map(this::buildResultResponse)
                .toList();
    }

    private ResultInfoResponse buildResultResponse(ResultInfo result) {
        return ResultInfoResponse.builder()
                .courseTitle(result.getCourseTitle())
                .courseCredit(result.getTotalCredit())
                .gradePoint(result.getPointEquivalent())
                .gradeLetter(result.getGradeLetter())
                .totalCgpa(result.getCgpa())
                .build();
    }

    private List<SemesterInfoResponse> buildSemesterResponseList(SemesterInfo[] semesters) {
        return Arrays.stream(semesters)
                .map(this::buildSemesterResponse)
                .toList();
    }

    private SemesterInfoResponse buildSemesterResponse(SemesterInfo semester) {
        return SemesterInfoResponse.builder()
                .semesterId(semester.getSemesterId())
                .semesterYear(semester.getSemesterYear())
                .semesterName(semester.getSemesterName())
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
