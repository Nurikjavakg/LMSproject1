package peaksoft.dto.student;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class StudentRequest {
    private String firstName;
    private String lastName;
    private Long phoneNumber;
    private String email;
    private String studyFormat;
}
