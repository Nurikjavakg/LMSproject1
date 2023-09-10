package peaksoft.repasitories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.company.CompanyResponse;
import peaksoft.dto.company.CompanyResponseInfo;
import peaksoft.entities.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("select new peaksoft.dto.company.CompanyResponse(c.id,c.name,c.country,c.address,c.phoneNumber,c.createdAt,c.updatedAt)from Company c")
    Page<CompanyResponse> getAllCompanies(Pageable pageable);

    @Query("select new peaksoft.dto.company.CompanyResponse(c.id,c.name,c.country,c.address,c.phoneNumber,c.createdAt,c.updatedAt)from Company c where c.id=:id")
    Optional<CompanyResponse> getCompaniesBy(Long id);

    Company getCompaniesByName(String name);

    @Query("""
            select new peaksoft.dto.company.CompanyResponseInfo(c.id,c.name,c.country,c.address,c.phoneNumber,c.createdAt,c.updatedAt)
            from Company c  
            where c.id =?1 group by c.id,c.name,c.country,c.address,c.phoneNumber,c.createdAt,c.updatedAt
            """)
    Optional<CompanyResponseInfo> getCompanyInfo(Long companyId);

    @Query("select cast(count (s) as int)from Company co join co.courseList c join c.groupList gr join gr.studentList s where c.id= :courseId")
    int countStudent(Long courseId);

    @Query("select c.courseName from Company co join co.courseList c where c.id= :courseId")
    List<String> courseName(Long courseId);

    @Query("select distinct gr.groupName from Company co join co.courseList c join c.groupList gr where c.id = :courseId")
    List<String> groupName(Long courseId);

    @Query("select distinct i.firstName from Company co join co.instructors i where co.id= :companyId")
    List<String> instructorName(Long companyId);


}