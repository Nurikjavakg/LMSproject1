package peaksoft.services;

import peaksoft.dto.admin.*;
import peaksoft.dto.simple.SimpleResponse;

public interface AdminService {
    SimpleResponse signUp(SignUpRequest request);
    UserResponseWithToken login(SignInRequest signInRequest);


}
