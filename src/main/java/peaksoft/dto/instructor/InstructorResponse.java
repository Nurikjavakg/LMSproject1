package peaksoft.dto.instructor;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class InstructorResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private Long phoneNumber;
    private String specialization;

    public InstructorResponse(Long id, String firstName, String lastName, Long phoneNumber, String specialization) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization ;

    }
}