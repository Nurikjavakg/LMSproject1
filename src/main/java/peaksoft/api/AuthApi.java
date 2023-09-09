package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.dto.admin.SignUpRequest;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.services.AdminService;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApi {
    private final AdminService adminService;


    @PostMapping("/login")
    public SimpleResponse login(@RequestBody SignUpRequest signUpRequest){
        ResponseEntity.ok(adminService.signUp(signUpRequest));
        return  SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully saved...")
                .build();
    }
}
