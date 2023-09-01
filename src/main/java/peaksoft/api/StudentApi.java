package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.lesson.LessonRequest;
import peaksoft.dto.lesson.LessonResponse;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.dto.student.StudentRequest;
import peaksoft.dto.student.StudentResponse;
import peaksoft.services.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentApi {
    private final StudentService studentService;


    @GetMapping("/{groupId}")
    public List<StudentResponse> getAllLessons(@PathVariable Long groupId) {
        return studentService.getAllStudents(groupId);
    }


    @PostMapping("/{groupId}")
    public SimpleResponse saveLesson(@PathVariable Long groupId, @RequestBody StudentRequest studentRequest) {
        studentService.saveStudent(groupId, studentRequest);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Student is saved")
                .build();
    }

    @GetMapping("/get/{studentId}")
    public StudentResponse getCourseById(@PathVariable Long studentId) {
        return studentService.getStudentId(studentId);

    }

    @PutMapping("/{groupId}/{studentId}")
    public SimpleResponse updateLesson(@PathVariable Long groupId, @PathVariable Long studentId, @RequestBody StudentRequest studentRequest) {
        studentService.updateStudent(groupId, studentId, studentRequest);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Student is updated...")
                .build();


    }

    @DeleteMapping("/{groupId}/{studentId}")
    public SimpleResponse deleteStudent(@PathVariable Long groupId, @PathVariable Long studentId) {
        studentService.deleteStudent(groupId, studentId);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Student is deleted...")
                .build();
    }

    @PutMapping("/blocked/{studentId}")
    public SimpleResponse block(@PathVariable Long studentId, Boolean isBlocked) {
        studentService.blockStudent(studentId, isBlocked);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Student is blocked...")
                .build();
    }

    @PutMapping("/anBlocked/{studentId}")
    public SimpleResponse anBlock(@PathVariable Long studentId, Boolean anBlocked) {
        studentService.anBlockStudent(studentId, anBlocked);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Student is an blocked...")
                .build();
    }
}