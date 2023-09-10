package peaksoft.api;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.admin.*;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.services.AdminService;


@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdministratorApi {
    private final AdminService adminService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<SimpleResponse>signUp(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(adminService.signUp(signUpRequest));
    }
    @PostMapping("/login")
    public ResponseEntity<UserResponseWithToken> login(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(adminService.login(signInRequest));
    }

}
