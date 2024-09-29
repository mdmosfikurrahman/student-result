package com.daffodil.studentresult.repository;

import com.daffodil.studentresult.model.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentInfoRepository extends JpaRepository<StudentInfo, String> {
    Optional<StudentInfo> findByStudentId(String studentId);
}
