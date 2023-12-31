package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.group.GroupRequest;
import peaksoft.dto.group.GroupResponse;
import peaksoft.dto.group.GroupResponseGetStudentCount;
import peaksoft.dto.instructor.InstructorRequest;
import peaksoft.dto.instructor.InstructorResponse;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.services.GroupService;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupApi {
    private final GroupService groupService;



   @GetMapping("/{courseId}")
   @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
   public List<GroupResponse> groupResponseList(@PathVariable Long courseId){
    return groupService.getAllGroup(courseId);

    }

    @PostMapping("/{courseId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public SimpleResponse saveGroup(@PathVariable Long courseId, @RequestBody GroupRequest groupRequest){
        groupService.saveGroup(courseId,groupRequest);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Group is saved")
                .build();
    }

    @GetMapping("/get/{groupId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public GroupResponse getGroupById(@PathVariable Long groupId) {
        return groupService.getGroupId(groupId);
    }

    @PutMapping("/{courseId}/{groupId}")
    public SimpleResponse updateGroup(@PathVariable Long courseId, @PathVariable Long groupId, @RequestBody GroupRequest groupRequest) {
        groupService.updateGroup(courseId,groupId,groupRequest);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Group is updated...")
                .build();
        }

    @DeleteMapping("/{groupId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public SimpleResponse deleteGroup(@PathVariable Long groupId){
       groupService.deleteGroup(groupId);
       return SimpleResponse.builder()
               .httpStatus(HttpStatus.OK)
               .message("Group is deleted...")
               .build();
    }

    @GetMapping("/getGroupStudents/{groupId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public GroupResponseGetStudentCount getGroupStudents(@PathVariable Long groupId){
      return groupService.groupStudents(groupId);
    }


  }
