package peaksoft.services;

import peaksoft.dto.lesson.LessonRequest;
import peaksoft.dto.lesson.LessonResponse;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.dto.student.StudentRequest;
import peaksoft.dto.student.StudentResponse;

import java.util.List;

public interface StudentService {
    SimpleResponse saveStudent(Long groupId, StudentRequest studentRequest);
    List<StudentResponse> getAllStudents(Long groupId);
    StudentResponse getStudentId(Long studentId);
    SimpleResponse updateStudent(Long groupId,Long studentId, StudentRequest studentRequest);
    SimpleResponse deleteStudent(Long groupId,Long studentId);
    SimpleResponse blockStudent(Long studentId, Boolean isBlock);
    SimpleResponse anBlockStudent(Long studentId, Boolean anBlock);
}
