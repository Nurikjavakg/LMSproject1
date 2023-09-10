package peaksoft.services.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.instructor.InstructorRequest;
import peaksoft.dto.instructor.InstructorResponse;
import peaksoft.dto.instructor.InstructorResponseGetStudents;
import peaksoft.dto.instructor.InstructorResponseInfo;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.entities.Company;
import peaksoft.entities.Instructor;
import peaksoft.exception.AlreadyExists;
import peaksoft.exception.NotFoundException;
import peaksoft.repasitories.CompanyRepository;
import peaksoft.repasitories.InstructorRepository;
import peaksoft.services.InstructorService;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final CompanyRepository companyRepository;


    @Override
    public SimpleResponse assign(Long companyId, Long instructorId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(()-> new NotFoundException("Company with id:"+companyId+" not found..."));
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(()-> new NotFoundException("Instructor with id:"+instructorId+" not found..."));
        if(instructor.getPhoneNumber()==null) {
            company.getInstructors().add(instructor);
            companyRepository.save(company);
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Instructor is updated...")
                    .build();
        }else {
                throw new AlreadyExists("Instructor with name"+instructor.getFirstName()+" "+instructor.getLastName()+" is exists");
        }
    }

    @Override
    public List<InstructorResponse> getAllInstructors(Long companyId) {
        List<InstructorResponse> instructors = instructorRepository.getInstructorsByCompanyId(companyId);
        if (instructors.isEmpty()) {
            throw new NotFoundException("Company not found with id:" + companyId);
        }return instructors;

    }
    @Override
    public InstructorResponse getInstructorById(Long instructorId) {
        InstructorResponse instructorResponse = instructorRepository.getInstructorsById(instructorId);
        if(instructorResponse == null){
            throw new NotFoundException("course with id:"+instructorId+" not found...");
        }
        return instructorResponse;

    }

    @Override
    public SimpleResponse updateInstructor(Long companyId, Long instructorId, InstructorRequest instructorRequest) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow( ()-> new NotFoundException("Not found company with id:"+companyId));
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow( ()-> new NotFoundException("Instructor with id:"+instructorId+" not found!" ));
        List<Company> companies = new ArrayList<>();
        companies.add(company);
        instructor.setCompanies(companies);

        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructor.setSpecialization(instructorRequest.getSpecialization());
        instructorRepository.save(instructor);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Instructor is updated...")
                .build();

    }


    @Override
    public SimpleResponse deleteInstructor(Long companyId, Long instructorId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow( ()-> new NotFoundException("Not found company with id:"+companyId));

       Instructor instructor = instructorRepository.findById(instructorId)
               .orElseThrow( ()-> new NotFoundException("Course with id:"+instructorId+" not found!" ));

       instructor.getCompanies().remove(company);
       company.getInstructors().remove(instructor);
        instructorRepository.delete(instructor);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Instructor is deleted...")
                .build();

    }

    @Override
    public InstructorResponseGetStudents getStudentCountFromInstructor(Long instructorId) {

        return instructorRepository.getStudentCountFromInstructor(instructorId)
                .orElseThrow( ()-> new NotFoundException("not found"));
    }

    @Override
    public InstructorResponseInfo getInstructorInfo(Long instructorId) {
        return instructorRepository.getInstructorInfo(instructorId)
                .orElseThrow( ()-> new NotFoundException("Instructor with id:"+instructorId+ " not found..."));
    }

}
