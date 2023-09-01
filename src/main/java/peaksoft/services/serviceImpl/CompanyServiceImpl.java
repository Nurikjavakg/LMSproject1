package peaksoft.services.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.company.CompanyRequest;
import peaksoft.dto.company.CompanyResponse;
import peaksoft.dto.company.CompanyResponseInfo;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.entities.Company;
import peaksoft.exception.InvalidNameException;
import peaksoft.exception.NotFoundException;
import peaksoft.repasitories.CompanyRepository;
import peaksoft.repasitories.CourseRepository;
import peaksoft.repasitories.GroupRepository;
import peaksoft.repasitories.InstructorRepository;
import peaksoft.services.CompanyService;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;
    private final GroupRepository groupRepository;

    @Override
    public List<CompanyResponse> getAllCompany() {
        return companyRepository.getAllCompanies();
    }

    @Override
    public SimpleResponse saveCompany(CompanyRequest companyRequest) {
        Company companyName = companyRepository.getCompaniesByName(companyRequest.getName());
        if (companyName == null) {
            Company company = new Company();
            company.setName(companyRequest.getName());
            company.setCountry(companyRequest.getCountry());
            company.setAddress(companyRequest.getAddress());
            company.setPhoneNumber(companyRequest.getPhoneNumber());
            ZonedDateTime currentTimestamp = ZonedDateTime.now();
            company.setCreatedAt(currentTimestamp);
            company.setUpdatedAt(currentTimestamp);


            companyRepository.save(company);
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message(String.format("Company with id: %s successfully", company.getId()))
                    .build();
        } else {
            throw new InvalidNameException(String.format("Company with name: %s exists", companyRequest.getName()));
        }
    }
    @Override
    public CompanyResponse getCompanyByID(Long id) {
        return companyRepository.getCompaniesBy(id).orElseThrow(()-> new NoSuchElementException("not found Company..."));
    }

    @Override
    public SimpleResponse updateCompany(Long companyId, CompanyRequest companyRequest) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow( ()-> new NoSuchElementException("company not found..."));

        company.setName(companyRequest.getName());
        company.setCountry(companyRequest.getCountry());
        company.setAddress(companyRequest.getAddress());
        company.setPhoneNumber(companyRequest.getPhoneNumber());
        ZonedDateTime currentTimestamp = ZonedDateTime.now();
        company.setUpdatedAt(currentTimestamp);
        companyRepository.save(company);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Company with id: %s successfully updated",company.getId()))
                .build();
    }

    @Override
    public SimpleResponse deleteCompany(Long id) {
        Company company = companyRepository.findById(id).orElseThrow( ()-> new NotFoundException("Company with id:"+id+"not found..."));
        companyRepository.delete(company);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("COMPANY IS DELETED...")
                .build();
    }

    @Override
    public CompanyResponseInfo getCompanyInfo(Long companyId) {
        CompanyResponseInfo companyInfo = companyRepository.getCompanyInfo(companyId).orElseThrow(()-> new NotFoundException("not found"));

        List<String> courseName = companyRepository.courseName(companyId);
        List<String> groupName = companyRepository.groupName(companyId);
        List<String> instructorName = companyRepository.instructorName(companyId);
        int countStudent = companyRepository.countStudent(companyId);

        companyInfo.setCourseName(courseName);
        companyInfo.setGroupName(groupName);
        companyInfo.setInstructorName(instructorName);
        companyInfo.setStudentCount(countStudent);

        return new CompanyResponseInfo(
                companyInfo.getId(),
                companyInfo.getName(),
                companyInfo.getCountry(),
                companyInfo.getAddress(),
                companyInfo.getPhoneNumber(),
                companyInfo.getCreatedAt(),
                companyInfo.getUpdatedAt(),

                companyInfo.getCourseName(),
                companyInfo.getGroupName(),
                companyInfo.getInstructorName(),
                companyInfo.getStudentCount()

        );
    } }


