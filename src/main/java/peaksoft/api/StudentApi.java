package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public List<StudentResponse> getAllStudents(@PathVariable Long groupId) {
        return studentService.getAllStudents(groupId);
    }



    @PostMapping("/{groupId}/{studentId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public SimpleResponse assignStudent(@PathVariable Long groupId, @PathVariable Long studentId) {
        studentService.assign(groupId, studentId);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Student is assigned to group...")
                .build();
    }

    @GetMapping("/{companyId}/studyFormat")
    public List<StudentResponse>filter(@PathVariable Long companyId, @RequestParam String studyFormat){
       return studentService.getAllOnlineOrOfflineStudents(companyId,studyFormat);
    }


    @GetMapping("/get/{studentId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public StudentResponse getStudentById(@PathVariable Long studentId) {
        return studentService.getStudentId(studentId);

    }

    @PutMapping("/{groupId}/{studentId}")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    public SimpleResponse updateLesson(@PathVariable Long groupId, @PathVariable Long studentId, @RequestBody StudentRequest studentRequest) {
        studentService.updateStudent(groupId, studentId, studentRequest);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Student is updated...")
                .build();


    }

    @DeleteMapping("/{groupId}/{studentId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public SimpleResponse deleteStudent(@PathVariable Long groupId, @PathVariable Long studentId) {
        studentService.deleteStudent(groupId, studentId);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Student is deleted...")
                .build();
    }

    @PutMapping("/blocked/{studentId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public SimpleResponse block(@PathVariable Long studentId, Boolean isBlocked) {
        studentService.blockStudent(studentId, isBlocked);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Student is blocked...")
                .build();
    }

    @PutMapping("/anBlocked/{studentId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public SimpleResponse anBlock(@PathVariable Long studentId, Boolean anBlocked) {
        studentService.anBlockStudent(studentId, anBlocked);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Student is an blocked...")
                .build();
    }
}