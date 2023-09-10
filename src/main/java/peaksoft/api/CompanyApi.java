package peaksoft.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.company.CompanyRequest;
import peaksoft.dto.company.CompanyResponse;
import peaksoft.dto.company.CompanyResponseInfo;
import peaksoft.dto.company.PaginationResponse;
import peaksoft.dto.simple.SimpleResponse;

import peaksoft.services.CompanyService;


import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyApi {
    private final CompanyService companyService;


    @GetMapping
    public PaginationResponse companies( @RequestParam int currentPage, @RequestParam int pageSize) {
        return companyService.getAllCompany(currentPage,pageSize);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse saveCompany(@RequestBody @Valid CompanyRequest companyRequest) {
        companyService.saveCompany(companyRequest);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Company with name: %s successfully saved!", companyRequest.getName()))
                .build();
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CompanyResponse getCompanyById(@PathVariable Long id) {
        return companyService.getCompanyByID(id);
    }
    @PutMapping("/{companyId}")
    public SimpleResponse updateCompanyId(@PathVariable Long companyId, @RequestBody CompanyRequest companyRequest){
        companyService.updateCompany(companyId,companyRequest);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Company with name: %s successfully updated!", companyRequest.getName()))
                .build();
    }
    @DeleteMapping("/{companyId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse deleteCompany(@PathVariable Long companyId){
        companyService.deleteCompany(companyId);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("company is deleted...")
                .build();
    }

    @GetMapping("/getInfo/{companyId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CompanyResponseInfo getInfo(@PathVariable Long companyId){
        return companyService.getCompanyInfo(companyId);
    }
}

