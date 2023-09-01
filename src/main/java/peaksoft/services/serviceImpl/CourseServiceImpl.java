package peaksoft.services.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import peaksoft.dto.course.CourseRequest;
import peaksoft.dto.course.CourseResponse;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.entities.Company;
import peaksoft.entities.Course;
import peaksoft.entities.Instructor;
import peaksoft.exception.InvalidNameException;
import peaksoft.exception.NotFoundException;
import peaksoft.repasitories.CompanyRepository;
import peaksoft.repasitories.CourseRepository;
import peaksoft.repasitories.InstructorRepository;
import peaksoft.services.CourseService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;
    private final InstructorRepository instructorRepository;
    @Override
    public SimpleResponse saveCourse(Long companyId, CourseRequest courseRequest) {
        Company company = companyRepository.findById(companyId).orElseThrow( (()-> new NotFoundException("Not found company...")));
        Course courseName = courseRepository.findByCourseName(courseRequest.getCourseName());
        if(courseName == null){
        Course course = new Course();
        course.setCompany(company);
        course.setCourseName(courseRequest.getCourseName());
        course.setDateOfStart(courseRequest.getDateOfStart());
        course.setDescription(courseRequest.getDescription());
        courseRepository.save(course);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Course is saved")
                .build();
        }else {
            throw new InvalidNameException(String.format("Course with name: %s exists", courseRequest.getCourseName()));
        }
    }

    @Override
    public SimpleResponse assignInstructorToCourse(Long courseId, Long instructorId) {
        Course course = courseRepository.findById(courseId).orElseThrow( ()-> new NotFoundException("Course with id:"+courseId+ " not found..."));
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow( ()-> new NotFoundException("Instructor with id:"+instructorId+" not found..."));
        course.setInstructor(instructor);
        courseRepository.save(course);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Instructor is assigned to course...")
                .build();
    }

    @Override
    public List<CourseResponse> getAllCourse(Long companyId) {
        List<CourseResponse> courses = courseRepository.getAllCourse(companyId);

        if (courses.isEmpty()) {
            throw new NotFoundException("Company not found with id:"+companyId);
        }return courses;

}
    @Override
    public CourseResponse getCourseId(Long courseId) {
       CourseResponse courseResponse = courseRepository.getCourseById(courseId);
       if(courseResponse == null){
           throw new NotFoundException("course with id:"+courseId+" not found...");
       }
        return courseResponse;
    }

    @Override
    public SimpleResponse updateCourse(Long companyId,Long courseId, CourseRequest courseRequest) {
        Company company = companyRepository.findById(companyId).orElseThrow( ()-> new NotFoundException("Not found company with id:"+companyId));
        Course course = courseRepository.findById(courseId).orElseThrow( ()-> new NotFoundException("Course with id:"+courseId+" not found!" ));
        Company company1 = companyRepository.save(company);
        course.setCompany(company1);
        course.setCourseName(courseRequest.getCourseName());
        course.setDateOfStart(courseRequest.getDateOfStart());
        course.setDescription(courseRequest.getDescription());
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Course is saved")
                .build();
    }

    @Override
    public SimpleResponse deleteCourse(Long companyId,Long courseId) {
        Company company = companyRepository.findById(companyId).orElseThrow( ()-> new NotFoundException("Not found company with id:"+companyId));
        Course course = courseRepository.findById(courseId).orElseThrow( ()-> new NotFoundException("Course with id:"+courseId+" not found!" ));
        Company company1 = companyRepository.save(company);
        course.setCompany(company1);
        courseRepository.delete(course);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Course is deleted...")
                .build();
    }

    @Override
    public List<CourseResponse> sortByDate(Long companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow( ()-> new NotFoundException("not found Company"));
        courseRepository.sortByDate(companyId);

        return ;
    }


}
