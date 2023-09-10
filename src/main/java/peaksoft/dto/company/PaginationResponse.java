package peaksoft.dto.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
public class PaginationResponse {
     private List<CompanyResponse> companies;
     private int currentPage;
     private int pageSize;

}
