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

//    @PostMapping("/{companyId}")
//    public ResponseEntity<UserResponse> signUp(@Valid @RequestBody AuthRequest administratorRequest, @PathVariable Long companyId){
//        return ResponseEntity.ok(adminService.saveAdministrator(companyId,administratorRequest));
//
//    }

//    @PutMapping("/{userId}")
//    @Secured({"USER", "ADMIN"})
//    public ResponseEntity<UserResponse> updateUser(@PathVariable Long userId,
//                                                   @RequestBody UserRequest userRequest,
//                                                   Principal principal){
//        return ResponseEntity.ok(userService.update(principal, userId, userRequest));
//    }
}
