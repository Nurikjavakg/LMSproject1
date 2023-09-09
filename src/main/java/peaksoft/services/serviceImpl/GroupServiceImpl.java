package peaksoft.services.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.group.GroupRequest;
import peaksoft.dto.group.GroupResponse;
import peaksoft.dto.group.GroupResponseGetStudentCount;
import peaksoft.dto.instructor.InstructorResponse;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.entities.Company;
import peaksoft.entities.Course;
import peaksoft.entities.Group;

import peaksoft.exception.InvalidNameException;
import peaksoft.exception.NotFoundException;
import peaksoft.repasitories.CourseRepository;
import peaksoft.repasitories.GroupRepository;
import peaksoft.services.GroupService;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
    @Override
    public SimpleResponse saveGroup(Long courseId, GroupRequest groupRequest) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException("Course with id:" + courseId + " not found"));
        Group groupName = groupRepository.findByGroupName(groupRequest.getGroupName());
        if (groupName == null) {
            Group group = new Group();
            group.setGroupName(groupRequest.getGroupName());
            group.setImageLink(groupRequest.getImageLink());
            group.setDescription(groupRequest.getDescription());
            group.getCourseList().add(course);
            groupRepository.save(group);
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Course is saved")
                    .build();
        } else {
            throw new InvalidNameException("Group with name:"+groupName+"exist");
        }
    }

    @Override
    public List<GroupResponse> getAllGroup(Long courseId) {
        List<GroupResponse> groups = groupRepository.getGroupsByCourseId(courseId);
        if (groups.isEmpty()) {
            throw new NotFoundException("Not found groups with courseId:" + courseId);
        } else {
            return groups;
        }
    }
    @Override
    public GroupResponse getGroupId(Long groupId) {
        GroupResponse groupResponse = groupRepository.getGroupsById(groupId);
        if(groupResponse == null){
            throw new NotFoundException("course with id:"+groupId+" not found...");
        }
        return groupResponse;
    }


    @Override
    public SimpleResponse updateGroup(Long courseId, Long groupId, GroupRequest groupRequest) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException("Course with id:" + courseId + " not found"));
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new NotFoundException("Group with id:" + groupId + " not found"));
        List<Course> course1 = new ArrayList<>();
        course1.add(course);
        group.setCourseList(course1);

        group.setGroupName(groupRequest.getGroupName());
        group.setImageLink(groupRequest.getImageLink());
        group.setDescription(groupRequest.getDescription());
        groupRepository.save(group);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Group is updated...")
                .build();
    }

    @Override
    public SimpleResponse deleteGroup(Long groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow( ()-> new NotFoundException("Group with id:"+groupId+" not found..."));
        groupRepository.delete(group);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Group is deleted...")
                .build();
    }

    @Override
    public GroupResponseGetStudentCount groupStudents(Long groupId) {
        return groupRepository.getGroupsStudent(groupId).orElseThrow( ()-> new NotFoundException("Not found group with id:"+groupId));

    }
}
