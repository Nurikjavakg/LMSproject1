package peaksoft.repasitories;

import org.springframework.data.jpa.repository.JpaRepository;




import peaksoft.entities.Student;



public interface StudentRepository extends JpaRepository<Student, Long> {


//    Student findByEmail(String email);
//    @Query("select new peaksoft.dto.student.StudentResponse(s.id,s.firstName,s.lastName,s.phoneNumber,s.email,s.studyFormat,s.isBlocked)from Student s join Group g on g.id = :groupId")
//    List<StudentResponse> findAllStudentsById(Long groupId);
//
//    StudentResponse findStudentById(Long studentId);
//    @Modifying
//    @Query("update Student s set s.isBlocked = :isBlocked where s.id = :studentId")
//    int blockStudent(@Param("studentId") Long studentId, @Param("isBlocked") Boolean isBlocked);

}


