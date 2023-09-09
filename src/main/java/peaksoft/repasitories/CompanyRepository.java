package peaksoft.repasitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.dto.company.CompanyResponse;
import peaksoft.dto.company.CompanyResponseInfo;
import peaksoft.entities.Company;


import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("select new peaksoft.dto.company.CompanyResponse(c.id,c.name,c.country,c.address,c.phoneNumber,c.createdAt,c.updatedAt)from Company c")
    List<CompanyResponse> getAllCompanies();

    @Query("select new peaksoft.dto.company.CompanyResponse(c.id,c.name,c.country,c.address,c.phoneNumber,c.createdAt,c.updatedAt)from Company c where c.id=:id")
    Optional<CompanyResponse> getCompaniesBy(Long id);

    Company getCompaniesByName(String name);

    @Query("""
            select new peaksoft.dto.company.CompanyResponseInfo(c.id,c.name,c.country,c.address,c.phoneNumber,c.createdAt,c.updatedAt)
            from Company c  
            where c.id =?1 group by c.id,c.name,c.country,c.address,c.phoneNumber,c.createdAt,c.updatedAt
            """)
    Optional<CompanyResponseInfo> getCompanyInfo(Long companyId);

    @Query("select cast(count (s) as int)from Company co join co.courseList c join c.groupList gr join gr.studentList s where s.id= :studentId")
     int countStudent(Long studentId);
     @Query("select c.courseName from Company co join co.courseList c where c.id= :courseId")
    List<String>courseName(Long courseId);

     @Query("select gr.groupName from Company co join co.courseList c join c.groupList gr where gr.id= :groupName")
     List<String>groupName(Long groupName);

     @Query("select i.firstName, i.lastName from Company co join co.instructors i where i.id= :instructorId")
    List<String>instructorName(Long instructorId);




}