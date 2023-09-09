package peaksoft.dto.admin;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Role;
@Getter
@Setter
@Builder
public class UserResponse {
    private Long id;
    private String email;
    private String password;
    private Role role;
    private String token;

    public UserResponse(Long id, String email, String password, Role role,String token) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.token = token;
    }
}
