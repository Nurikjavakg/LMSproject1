package peaksoft.dto.admin;
import peaksoft.enums.Role;
import lombok.Builder;

@Builder
public record SignUpRequest(
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        String password,
        Role role,
        String studyFormat,
        String specialization
) {
}