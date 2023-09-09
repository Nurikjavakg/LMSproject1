package peaksoft.dto.instructor;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class InstructorResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Long phoneNumber;
    private String specialization;

    public InstructorResponse(Long id, String firstName, String lastName, String email, String password, Long phoneNumber, String specialization) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
    }
}