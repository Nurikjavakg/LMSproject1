package peaksoft.dto.group;

import lombok.Getter;

@Getter
public class GroupResponseGetStudentCount {
    private Long id;
    private String groupName;
    private int studentCount;

    public GroupResponseGetStudentCount(Long id, String groupName, int studentCount) {
        this.id = id;
        this.groupName = groupName;
        this.studentCount = studentCount;
    }
}
