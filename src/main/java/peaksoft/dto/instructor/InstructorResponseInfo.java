package peaksoft.dto.instructor;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InstructorResponseInfo {

    private Long id;
    private String firstName;
    private String lastName;
    private Long phoneNumber;
    private String specialization;
    private String groupName;
    private int studentCount;

    public InstructorResponseInfo(Long id, String firstName, String lastName, Long phoneNumber, String specialization, String groupName, int studentCount) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
        this.groupName = groupName;
        this.studentCount = studentCount;
    }
}
