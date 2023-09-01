package peaksoft.repasitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.group.GroupResponse;
import peaksoft.dto.instructor.InstructorResponse;
import peaksoft.dto.instructor.InstructorResponseGetStudents;
import peaksoft.dto.instructor.InstructorResponseInfo;
import peaksoft.entities.Instructor;

import java.util.List;
import java.util.Optional;
import java.util.Spliterator;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    List<InstructorResponse> getInstructorsByCompaniesId(Long companyId);

    InstructorResponse getInstructorsById(Long instructorId);

   @Query("select new peaksoft.dto.instructor.InstructorResponseGetStudents (i.id, concat(i.firstName,' ',i.lastName),cast(count (s.id) as int )) from Instructor i join i.courseList co join co.groupList g join g.studentList s where i.id = ?1 group by i.id,concat(i.firstName,' ',i.lastName)")
    Optional<InstructorResponseGetStudents> getStudentCountFromInstructor(Long instructorId);

    @Query("SELECT NEW peaksoft.dto.instructor.InstructorResponseInfo(i.id, i.firstName, i.lastName, i.phoneNumber, i.specialization, gr.groupName, CAST(COUNT(s.id) AS int)) " +
            "FROM Instructor i " +
            "JOIN i.courseList co " +
            "JOIN co.groupList gr " +
            "JOIN gr.studentList s " +
            "WHERE i.id = :instructorId " +
            "GROUP BY i.id, i.firstName, i.lastName, i.phoneNumber, i.specialization, gr.groupName")
   Optional<InstructorResponseInfo>getInstructorInfo(Long instructorId);

}