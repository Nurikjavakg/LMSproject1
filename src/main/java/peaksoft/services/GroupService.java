package peaksoft.services;

import peaksoft.dto.group.GroupRequest;
import peaksoft.dto.group.GroupResponse;
import peaksoft.dto.group.GroupResponseGetStudentCount;
import peaksoft.dto.instructor.InstructorResponseGetStudents;
import peaksoft.dto.instructor.InstructorResponseInfo;
import peaksoft.dto.simple.SimpleResponse;

import java.util.List;
import java.util.Optional;

public interface GroupService {
    SimpleResponse saveGroup(Long courseId, GroupRequest groupRequest);
    List<GroupResponse> getAllGroup(Long courseId);
    GroupResponse getGroupId(Long groupId);
    SimpleResponse updateGroup(Long courseId,Long groupId, GroupRequest groupRequest);
    SimpleResponse deleteGroup(Long groupId);
    GroupResponseGetStudentCount groupStudents(Long groupId);


}
