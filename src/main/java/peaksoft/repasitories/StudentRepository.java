package peaksoft.repasitories;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.dto.student.StudentResponse;
import peaksoft.entities.Student;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Long> {


    Student findByEmail(String email);
    @Query("select new peaksoft.dto.student.StudentResponse(s.id,s.firstName,s.lastName,s.phoneNumber,s.email,s.studyFormat,s.isBlocked)from Student s join Group g on g.id = :groupId")
    List<StudentResponse> findAllStudentsById(Long groupId);

    StudentResponse findStudentById(Long studentId);
    @Modifying
    @Query("update Student s set s.isBlocked = :isBlocked where s.id = :studentId")
    int blockStudent(@Param("studentId") Long studentId, @Param("isBlocked") Boolean isBlocked);
    @Query("select new peaksoft.dto.student.StudentResponse(s.id, s.firstName, s.lastName, s.phoneNumber,s.email, s.studyFormat,s.isBlocked)from Company c join c.courseList co join co.groupList gr join gr.studentList s where c.id = :companyId and s.studyFormat = :studyFormat")
    List<StudentResponse> getAllOnlineOrOfflineStudents(Long companyId, String studyFormat);

}


