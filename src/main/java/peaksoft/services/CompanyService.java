package peaksoft.services;

import peaksoft.dto.company.CompanyRequest;
import peaksoft.dto.company.CompanyResponse;
import peaksoft.dto.company.CompanyResponseInfo;
import peaksoft.dto.company.PaginationResponse;
import peaksoft.dto.simple.SimpleResponse;

public interface CompanyService {
    PaginationResponse getAllCompany( int currentPage, int pageSize);
    SimpleResponse saveCompany(CompanyRequest companyRequest);
    CompanyResponse getCompanyByID(Long id);
    SimpleResponse updateCompany(Long companyId,CompanyRequest companyRequest);
    SimpleResponse deleteCompany(Long id);
    CompanyResponseInfo getCompanyInfo(Long companyId);
}
