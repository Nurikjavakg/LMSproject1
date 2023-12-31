package peaksoft.services;



import peaksoft.dto.simple.SimpleResponse;
import peaksoft.dto.student.StudentRequest;
import peaksoft.dto.student.StudentResponse;

import java.util.List;

public interface StudentService {

    SimpleResponse assign(Long groupId, Long studentId);
    List<StudentResponse> getAllStudents(Long groupId);
    StudentResponse getStudentId(Long studentId);
    SimpleResponse updateStudent(Long groupId,Long studentId, StudentRequest studentRequest);
    SimpleResponse deleteStudent(Long groupId,Long studentId);
    SimpleResponse blockStudent(Long studentId, Boolean isBlock);
    SimpleResponse anBlockStudent(Long studentId, Boolean anBlock);
    List<StudentResponse> getAllOnlineOrOfflineStudents(Long companyId, String studyFormat);


}
