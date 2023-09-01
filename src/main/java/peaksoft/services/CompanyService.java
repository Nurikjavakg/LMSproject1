package peaksoft.services;

import peaksoft.dto.company.CompanyRequest;
import peaksoft.dto.company.CompanyResponse;
import peaksoft.dto.company.CompanyResponseInfo;
import peaksoft.dto.simple.SimpleResponse;

import java.util.List;

public interface CompanyService {
    List<CompanyResponse>getAllCompany();
    SimpleResponse saveCompany(CompanyRequest companyRequest);
    CompanyResponse getCompanyByID(Long id);
    SimpleResponse updateCompany(Long companyId,CompanyRequest companyRequest);
    SimpleResponse deleteCompany(Long id);
    CompanyResponseInfo getCompanyInfo(Long companyId);
}
