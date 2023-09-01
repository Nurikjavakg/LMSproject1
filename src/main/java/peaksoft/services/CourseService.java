package peaksoft.services;


import peaksoft.dto.course.CourseRequest;
import peaksoft.dto.course.CourseResponse;
import peaksoft.dto.simple.SimpleResponse;


import java.util.List;

public interface CourseService {
    SimpleResponse saveCourse(Long companyId, CourseRequest courseRequest);
    SimpleResponse assignInstructorToCourse(Long courseId,Long instructorId);
    List<CourseResponse> getAllCourse(Long companyId);
    CourseResponse getCourseId(Long courseId);
    SimpleResponse updateCourse(Long companyId,Long courseId, CourseRequest courseRequest);
    SimpleResponse deleteCourse(Long companyId,Long courseId);
    List<CourseResponse>sortByDate(Long companyId);
}
