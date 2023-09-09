package peaksoft.dto.admin;

public record SignInRequest(
        String email,
        String password
) {
}
