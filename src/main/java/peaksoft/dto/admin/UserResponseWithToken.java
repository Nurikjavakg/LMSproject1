package peaksoft.dto.admin;

import peaksoft.enums.Role;

public record UserResponseWithToken(
        Long id,
        String email,
        Role role,
        String token
){
}
