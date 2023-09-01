package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.lesson.LessonRequest;
import peaksoft.dto.lesson.LessonResponse;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.services.LessonService;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonApi {
    private final LessonService lessonService;


    @GetMapping("/{courseId}")
    public List<LessonResponse> getAllLessons(@PathVariable Long courseId) {
        return lessonService.getAllGroup(courseId);
    }

    @PostMapping("/{courseId}")
    public SimpleResponse saveLesson(@PathVariable Long courseId, @RequestBody LessonRequest lessonRequest) {
        lessonService.saveLesson(courseId, lessonRequest);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Lesson is saved")
                .build();
    }

    @GetMapping("/get/{lessonId}")
    public LessonResponse getCourseById(@PathVariable Long lessonId) {
        return lessonService.getLessonId(lessonId);

    }

    @PutMapping("/{courseId}/{lessonId}")
    public SimpleResponse updateLesson(@PathVariable Long courseId, @PathVariable Long lessonId, @RequestBody LessonRequest lessonRequest) {
        lessonService.updateLesson(courseId, lessonId, lessonRequest);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Lesson is updated...")
                .build();


    }
    @DeleteMapping("/{courseId}/{lessonId}")
    public SimpleResponse deleteLesson(@PathVariable Long courseId,@PathVariable Long lessonId){
        lessonService.deleteLesson(courseId,lessonId);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Lesson is deleted...")
                .build();

    }
}