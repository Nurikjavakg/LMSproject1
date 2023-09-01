package peaksoft.repasitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.course.CourseResponse;
import peaksoft.entities.Course;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("select new peaksoft.dto.course.CourseResponse(co.id,co.courseName,co.dateOfStart,co.description)from Course co join Company c on c.id =:companyId")
    List<CourseResponse> getAllCourse(Long companyId);


    CourseResponse getCourseById(Long courseId);

    Course findByCourseName(String courseName);
    @Query("select new peaksoft.dto.course.CourseResponse(co.id,co.courseName,co.dateOfStart,co.description)from Company c join c.courseList co where c.id= :companyId order by co.dateOfStart asc")
    List<CourseResponse>sortByDate(Long companyId);
}