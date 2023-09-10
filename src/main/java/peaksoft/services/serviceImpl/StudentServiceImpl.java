package peaksoft.services.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.dto.student.StudentRequest;
import peaksoft.dto.student.StudentResponse;

import peaksoft.entities.Group;
import peaksoft.entities.Student;
import peaksoft.exception.AlreadyExists;
import peaksoft.exception.NotFoundException;
import peaksoft.repasitories.GroupRepository;
import peaksoft.repasitories.StudentRepository;
import peaksoft.services.StudentService;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;


    @Override
    public SimpleResponse assign(Long groupId, Long studentId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new NotFoundException("Course with id:" + groupId + " not found..."));
        Student student = studentRepository.findById(studentId).orElseThrow(()-> new NotFoundException("Student with id:"+studentId+" not found..."));
        if(student.getEmail()==null) {
            student.setGroup(group);
            studentRepository.save(student);
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Student assigned to group...")
                    .build();

        }else {
            throw new AlreadyExists("Student with name:"+student.getFirstName()+" "+student.getLastName()+" is exists");
        }
    }


    @Override
    public List<StudentResponse> getAllStudents(Long groupId) {
        List<StudentResponse> students = studentRepository.findAllStudentsById(groupId);

        if (students.isEmpty()) {
            throw new NotFoundException("Company not found with id:"+groupId);
        }return students;


    }


    @Override
    public StudentResponse getStudentId(Long studentId) {
        StudentResponse studentResponse = studentRepository.findStudentById(studentId);
        if (studentResponse == null) {
            throw new NotFoundException("course with id:" + studentId + " not found...");
        }
        return studentResponse;

    }




    @Override
    public SimpleResponse updateStudent(Long groupId, Long studentId, StudentRequest studentRequest) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new NotFoundException("Course with id:" + groupId + " not found"));
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new NotFoundException("Lesson with id:" + studentId + " not found"));
        List<Group> groups= new ArrayList<>();
        groups.add(group);
        student.setGroup(group);

        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setEmail(studentRequest.getEmail());

        studentRepository.save(student);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Student is updated...")
                .build();

    }



    @Override
    public SimpleResponse deleteStudent(Long groupId, Long studentId) {
        Group group = groupRepository.findById(groupId).orElseThrow( ()-> new NotFoundException("Group with id:"+groupId+" not found..."));
        Student student = studentRepository.findById(studentId).orElseThrow( ()-> new NotFoundException("Student with id:"+studentId+ "not found..."));
        group.getStudentList().remove(student);
        studentRepository.delete(student);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Student is deleted...")
                .build();

    }

    @Override
    public SimpleResponse blockStudent(Long studentId, Boolean isBlock) {
        Student student = studentRepository.findById(studentId).orElseThrow(()-> new NotFoundException("Student with id:"+studentId+"not found"));
        student.setIsBlocked(true);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Student is blocked...")
                .build();

    }

    @Override
    public SimpleResponse anBlockStudent(Long studentId, Boolean anBlock) {
        Student student = studentRepository.findById(studentId).orElseThrow(()-> new NotFoundException("Student with id:"+studentId+"not found"));
        student.setIsBlocked(false);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Student is an blocked...")
                .build();

    }

    @Override
    public List<StudentResponse> getAllOnlineOrOfflineStudents(Long companyId, String studyFormat) {
        return studentRepository.getAllOnlineOrOfflineStudents(companyId,studyFormat);

    }


}
