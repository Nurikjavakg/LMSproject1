package peaksoft.services.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import peaksoft.dto.company.CompanyRequest;
import peaksoft.dto.company.CompanyResponse;
import peaksoft.dto.company.CompanyResponseInfo;
import peaksoft.dto.company.PaginationResponse;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.entities.Company;
import peaksoft.exception.InvalidNameException;
import peaksoft.exception.NotFoundException;
import peaksoft.repasitories.CompanyRepository;
import peaksoft.services.CompanyService;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;


    @Override
    public PaginationResponse getAllCompany( int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage-1, pageSize);
        Page<CompanyResponse> getAllCompanies = companyRepository.getAllCompanies(pageable);
        return PaginationResponse.builder()
                .companies(getAllCompanies.getContent())
                .currentPage(getAllCompanies.getNumber()+1)
                .pageSize(getAllCompanies.getTotalPages())
                .build();
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
            String message = String.format("Company with id: %s successfully", company.getId() + "saved...");
            log.info(message);
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message(message)
                    .build();
        } else {
            log.info(String.format("Company with name: %s exists", companyRequest.getName()));
            throw new InvalidNameException(String.format("Company with name: %s exists", companyRequest.getName()));

        }

    }

    @Override
    public CompanyResponse getCompanyByID(Long id) {
        log.info("Company with id:" + id.toString() + " found...");
        return companyRepository.getCompaniesBy(id)
                .orElseThrow(() -> {
                    String message = "not found Company...";
                    log.error(message);
                    return new NotFoundException(message);
                });
    }

    @Override
    public SimpleResponse updateCompany(Long companyId, CompanyRequest companyRequest) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new NoSuchElementException("company not found..."));

        company.setName(companyRequest.getName());
        company.setCountry(companyRequest.getCountry());
        company.setAddress(companyRequest.getAddress());
        company.setPhoneNumber(companyRequest.getPhoneNumber());
        ZonedDateTime currentTimestamp = ZonedDateTime.now();
        company.setUpdatedAt(currentTimestamp);
        companyRepository.save(company);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Company with id: %s successfully updated", company.getId()))
                .build();
    }

    @Override
    public SimpleResponse deleteCompany(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new NotFoundException("Company with id:" + id + "not found..."));
        companyRepository.delete(company);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("COMPANY IS DELETED...")
                .build();
    }

    @Override
    public CompanyResponseInfo getCompanyInfo(Long companyId) {
        CompanyResponseInfo companyInfo = companyRepository.getCompanyInfo(companyId)
                .orElseThrow(() -> new NotFoundException("not found"));

        List<String> courseName = companyRepository.courseName(companyId);
        List<String> groupName = companyRepository.groupName(companyId);
        List<String> instructorName = companyRepository.instructorName(companyId);
        int countStudent = companyRepository.countStudent(companyId);

        companyInfo.setCourseName(courseName);
        companyInfo.setGroupName(groupName);
        companyInfo.setInstructorName(instructorName);
        companyInfo.setStudentCount(countStudent);
        return companyInfo;

    }
}


