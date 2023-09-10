package peaksoft.dto.instructor;

import lombok.Getter;
import lombok.Setter;
import peaksoft.validation.PhoneNumberValidation;

@Getter
@Setter
public class InstructorRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @PhoneNumberValidation
    private String phoneNumber;
    private String specialization;
}
