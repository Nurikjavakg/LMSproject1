package peaksoft.services.serviceImpl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import peaksoft.config.JwtService;
import peaksoft.dto.admin.*;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.entities.Instructor;
import peaksoft.entities.Student;
import peaksoft.entities.User;
import peaksoft.enums.Role;

import peaksoft.exception.NotFoundException;
import peaksoft.repasitories.InstructorRepository;
import peaksoft.repasitories.StudentRepository;
import peaksoft.repasitories.UserRepository;
import peaksoft.services.AdminService;

import java.time.LocalDate;
import java.util.NoSuchElementException;

/**
 * Akylai Musaeva
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;
    private final JwtService jwtService;
    @Override
    public SimpleResponse signUp(SignUpRequest request) {
        if (request.role().equals(Role.INSTRUCTOR)) {
            Instructor instructor = convertRequestToInstructor(request);
            User user = convertRequestToUser(request);
            instructor.setUser(user);
            user.setInstructor(instructor);
            instructorRepository.save(instructor);
            userRepository.save(user);
            log.info("Successfully saved instructor with id: " + instructor.getId());
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully saved instructor with id: " + instructor.getId())
                    .build();
        } else if (request.role().equals(Role.STUDENT)) {
            Student student = convertRequestToStudent(request);
            User user = convertRequestToUser(request);
            student.setUser(user);
            user.setStudent(student);
            studentRepository.save(student);
            userRepository.save(user);
            log.info("Successfully saved instructor with id: " + student.getId());
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully saved instructor with id: " + student.getId())
                    .build();
        } else {
            log.info("Role is invalid!");
            throw new NotFoundException("Role is invalid!");
        }
    }
    private Student convertRequestToStudent(SignUpRequest request) {
        Student student = new Student();
        student.setFirstName(request.firstName());
        student.setEmail(request.email());
        student.setPassword(passwordEncoder.encode(request.password()));
        student.setLastName(request.lastName());
        student.setStudyFormat(request.studyFormat());

        student.setIsBlocked(false);
        return student;
    }


    private Instructor convertRequestToInstructor(SignUpRequest request) {
        Instructor instructor = new Instructor();
        instructor.setFirstName(request.firstName());
        instructor.setLastName(request.lastName());
        instructor.setPhoneNumber(request.phoneNumber());
        instructor.setSpecialization(request.specialization());
        instructor.setCreatedAt(LocalDate.now());
        return instructor;
    }
    private User convertRequestToUser(SignUpRequest request) {
        User user = new User();
        user.setEmail(request.email());
        user.setRole(request.role());
        user.setPassword(passwordEncoder.encode(request.password()));
        return user;
    }



    @PostConstruct
    public void initSaveAdmin() {
        User user = User.builder()
                .email("admin@gmail.com")
                .password(passwordEncoder.encode("admin"))
                .role(Role.ADMIN)
                .build();
        if (!userRepository.existsByEmail(user.getEmail())) {
            userRepository.save(user);
        }
    }
    @Override
    public UserResponseWithToken login(SignInRequest signInRequest) {
        User user = userRepository.getUserByEmail(signInRequest.email()).orElseThrow(() ->
                new RuntimeException(String.format("User with email: %s not found!", signInRequest.email())));

        String password = signInRequest.password();
        String dbEncodePassword = user.getPassword();

        if (!passwordEncoder.matches(password, dbEncodePassword)){
            throw new RuntimeException("Invalid password");
        }
        String token = jwtService.generateToken(user);

        return new UserResponseWithToken(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                token
        );
    }
}
