package peaksoft.dto.instructor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorRequest {
    private String firstName;
    private String lastName;
    private Long phoneNumber;
    private String specialization;
}
