package peaksoft.services.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.lesson.LessonRequest;
import peaksoft.dto.lesson.LessonResponse;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.entities.Course;
import peaksoft.entities.Lesson;
import peaksoft.exception.InvalidNameException;
import peaksoft.exception.NotFoundException;
import peaksoft.repasitories.CourseRepository;
import peaksoft.repasitories.LessonRepository;
import peaksoft.services.LessonService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;


    @Override
    public SimpleResponse saveLesson(Long courseId, LessonRequest lessonRequest) {
        Course course = courseRepository.findById(courseId).orElseThrow( ()-> new NotFoundException("Course with id:"+courseId+" not found..."));
        Lesson lessonName = lessonRepository.findByLessonName(lessonRequest.getLessonName());
        if(lessonName == null) {
            Lesson lesson = new Lesson();
            lesson.setLessonName(lessonRequest.getLessonName());
            lesson.setCourse(course);
            lessonRepository.save(lesson);

            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Lesson is saved")
                    .build();
        }else {
            throw new InvalidNameException("Lesson with name:"+lessonName+" exists, try with another name...");
        }
    }

    @Override
    public List<LessonResponse> getAllGroup(Long courseId) {
        List<LessonResponse> lessons = lessonRepository.getLessonsByCourseId(courseId);

        if (lessons.isEmpty()) {
            throw new NotFoundException("Company not found with id:"+courseId);
        }return lessons;

    }

    @Override
    public LessonResponse getLessonId(Long lessonId) {
            LessonResponse lessonResponse = lessonRepository.getLessonsById(lessonId);
            if (lessonResponse == null) {
                throw new NotFoundException("course with id:" + lessonId + " not found...");
            }
            return lessonResponse;
        }


    @Override
    public SimpleResponse updateLesson(Long courseId, Long lessonId, LessonRequest lessonRequest) {
            Course course = courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException("Course with id:" + courseId + " not found"));
            Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new NotFoundException("Lesson with id:" + lessonId + " not found"));
            List<Course> course1 = new ArrayList<>();
            course1.add(course);
            lesson.setCourse(course);

            lesson.setLessonName(lessonRequest.getLessonName());
            lessonRepository.save(lesson);
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Lesson is updated...")
                    .build();
        }



    @Override
    public SimpleResponse deleteLesson(Long courseId, Long lessonId) {
        Course course = courseRepository.findById(courseId).orElseThrow( ()-> new NotFoundException("Course with id:"+courseId+" not found..."));
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow( ()-> new NotFoundException("Lesson with id:"+lessonId+" not found..."));
        course.getLessonList().remove(lesson);
        lessonRepository.delete(lesson);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Lesson is deleted...")
                .build();
    }
}
