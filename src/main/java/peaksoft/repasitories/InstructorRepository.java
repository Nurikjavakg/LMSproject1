package peaksoft.repasitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Repository;

import peaksoft.dto.instructor.InstructorResponse;
import peaksoft.dto.instructor.InstructorResponseGetStudents;
import peaksoft.dto.instructor.InstructorResponseInfo;
import peaksoft.entities.Instructor;


import java.util.List;
import java.util.Optional;


@Repository
@EnableWebSecurity
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    @Query("SELECT NEW peaksoft.dto.instructor.InstructorResponse(i.id, i.firstName, i.lastName,i.phoneNumber, i.specialization) FROM Company c JOIN c.instructors i WHERE c.id = :companyId GROUP BY i.id, i.firstName, i.lastName, i.phoneNumber, i.specialization")
    List<InstructorResponse> getInstructorsByCompanyId(Long companyId);

    InstructorResponse getInstructorsById(Long instructorId);

   @Query("select new peaksoft.dto.instructor.InstructorResponseGetStudents (i.id, concat(i.firstName,' ',i.lastName),cast(count(s.id) as int)) from Instructor i join i.courses c join c.groupList gr join gr.studentList s where i.id = ?1 group by i.id,concat(i.firstName,' ',i.lastName)")
    Optional<InstructorResponseGetStudents> getStudentCountFromInstructor(Long instructorId);

    @Query("SELECT NEW peaksoft.dto.instructor.InstructorResponseInfo(i.id, i.firstName, i.lastName, i.phoneNumber, i.specialization) " +
            "FROM Instructor i " +

            "WHERE i.id = :instructorId " +
            "GROUP BY i.id, i.firstName, i.lastName, i.phoneNumber, i.specialization")
   Optional<InstructorResponseInfo>getInstructorInfo(Long instructorId);

}