package peaksoft.dto.student;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private Long phoneNumber;
    private String email;
    private String studyFormat;
    private Boolean isBlocked;

    public StudentResponse(Long id, String firstName, String lastName, Long phoneNumber, String email, String studyFormat, Boolean isBlocked) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.studyFormat = studyFormat;
        this.isBlocked = isBlocked;
    }
}

