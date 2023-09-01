package peaksoft.services;

import peaksoft.dto.course.CourseRequest;
import peaksoft.dto.course.CourseResponse;
import peaksoft.dto.instructor.InstructorRequest;
import peaksoft.dto.instructor.InstructorResponse;
import peaksoft.dto.instructor.InstructorResponseGetStudents;
import peaksoft.dto.instructor.InstructorResponseInfo;
import peaksoft.dto.simple.SimpleResponse;

import java.util.List;
import java.util.Optional;

public interface InstructorService {
    SimpleResponse saveInstructor(Long companyId, InstructorRequest instructorRequest);
    List<InstructorResponse> getAllInstructors(Long companyId);
    InstructorResponse getInstructorById(Long instructorId);
    SimpleResponse updateInstructor(Long companyId,Long instructorId, InstructorRequest instructorRequest);
    SimpleResponse deleteInstructor(Long companyId,Long instructorId);
    InstructorResponseGetStudents getStudentCountFromInstructor(Long instructorId);
    InstructorResponseInfo getInstructorInfo(Long instructorId);
}
