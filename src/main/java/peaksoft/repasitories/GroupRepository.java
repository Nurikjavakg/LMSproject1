package peaksoft.repasitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.group.GroupResponse;
import peaksoft.dto.group.GroupResponseGetStudentCount;
import peaksoft.entities.Group;
import peaksoft.entities.Student;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByGroupName(String groupName);
    @Query("select new peaksoft.dto.group.GroupResponse(g.id,g.groupName,g.imageLink,g.description)from Group g join Course c on c.id = :courseId")
    List<GroupResponse> getGroupsByCourseId(Long courseId);

    GroupResponse getGroupsById(Long groupId);

    @Query("select new peaksoft.dto.group.GroupResponseGetStudentCount(g.id,concat(g.groupName,''), cast(count (s.id) as int))from Group g join g.studentList s where g.id = ?1 group by g.id, concat(g.groupName,'') ")
    Optional<GroupResponseGetStudentCount>getGroupsStudent(Long groupId);
}