package peaksoft.dto.company;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class CompanyRequest {
    private String name;
    private String country;
    private String address;
    private Long phoneNumber;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;


}
