package peaksoft.services;

import org.springframework.stereotype.Service;
import peaksoft.dto.admin.*;
import peaksoft.dto.simple.SimpleResponse;

public interface AdminService {
    SimpleResponse signUp(SignUpRequest request);
    UserResponseWithToken login(SignInRequest signInRequest);


}
