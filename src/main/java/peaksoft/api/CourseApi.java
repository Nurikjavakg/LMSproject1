package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.course.CourseRequest;
import peaksoft.dto.course.CourseResponse;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.services.CourseService;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseApi {
    private final CourseService courseService;

    @GetMapping("/{companyId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public List<CourseResponse> getAllCourse(@PathVariable Long companyId) {
        return courseService.getAllCourse(companyId);
    }

    @PostMapping("/{companyId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public SimpleResponse saveCourse(@PathVariable Long companyId, @RequestBody CourseRequest courseRequest) {
        return  courseService.saveCourse(companyId, courseRequest);

    }

    @GetMapping("/get/{courseId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public CourseResponse getCourseById(@PathVariable Long courseId) {
        return courseService.getCourseId(courseId);


    }

    @PostMapping("/{courseId}/{instructorId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SimpleResponse assign(@PathVariable Long courseId,@PathVariable Long instructorId){
        courseService.assignInstructorToCourse(courseId,instructorId);

      return  SimpleResponse.builder()
              .httpStatus(HttpStatus.OK)
              .message("Instructor is assigned to course...")
              .build();
   }
    @PutMapping("/{companyId}/{courseId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SimpleResponse updateCourse(@PathVariable Long companyId, @PathVariable Long courseId, @RequestBody CourseRequest courseRequest){
        courseService.updateCourse(companyId,courseId,courseRequest);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Course is updated...")
                .build();


    }
    @DeleteMapping("/{companyId}/{courseId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SimpleResponse deleteCourse(@PathVariable Long companyId,@PathVariable Long courseId){
        courseService.deleteCourse(companyId,courseId);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Course is deleted...")
                .build();
    }
    @GetMapping("/sort/{companyId}")
    public void sortByDate(@PathVariable Long companyId){
        courseService.sortByDate(companyId);
    }

}
