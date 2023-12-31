package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.course.CourseRequest;
import peaksoft.dto.instructor.InstructorRequest;
import peaksoft.dto.instructor.InstructorResponse;
import peaksoft.dto.instructor.InstructorResponseGetStudents;
import peaksoft.dto.instructor.InstructorResponseInfo;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.services.InstructorService;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/instructors")
@RequiredArgsConstructor
public class InstructorApi {
    private final InstructorService instructorService;

    @GetMapping("/{companyId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public List<InstructorResponse> getAllInstructor(@PathVariable Long companyId) {
        return instructorService.getAllInstructors(companyId);
    }

    @PostMapping("/{companyId}/{instructorId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SimpleResponse assignInstructor(@PathVariable Long companyId,@PathVariable Long instructorId){
        instructorService.assign(companyId,instructorId);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Instructor is assigned to company...")
                .build();
    }



    @GetMapping("/get/{instructorId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public InstructorResponse getInstructorById(@PathVariable Long instructorId) {
        return instructorService.getInstructorById(instructorId);
    }

    @PutMapping("/{companyId}/{instructorId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SimpleResponse updateInstructor(@PathVariable Long companyId, @PathVariable Long instructorId, @RequestBody InstructorRequest instructorRequest) {
         instructorService.updateInstructor(companyId,instructorId,instructorRequest);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Instructor is updated...")
                .build();
    }
    @DeleteMapping("/{companyId}/{instructorId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SimpleResponse deleteInstructor(@PathVariable Long companyId,@PathVariable Long instructorId){
        instructorService.deleteInstructor(companyId,instructorId);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Instructor is deleted...")
                .build();
    }

    @GetMapping("/getCount/{instructorId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public InstructorResponseGetStudents getStudents(@PathVariable Long instructorId){
        return instructorService.getStudentCountFromInstructor(instructorId);
    }

    @GetMapping("/info/{instructorId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public InstructorResponseInfo getInstructorInfo(@PathVariable Long instructorId){
        return instructorService.getInstructorInfo(instructorId);
    }


}