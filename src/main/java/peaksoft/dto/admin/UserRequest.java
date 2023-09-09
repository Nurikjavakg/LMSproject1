package peaksoft.dto.admin;

public record UserRequest(
        String email,
        String password
) {
}
