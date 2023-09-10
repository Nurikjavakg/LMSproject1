package peaksoft.dto.company;

import lombok.Getter;
import lombok.Setter;
import peaksoft.validation.PhoneNumberValidation;

import java.time.ZonedDateTime;

@Getter
@Setter
public class CompanyRequest {
    private String name;
    private String country;
    private String address;
    @PhoneNumberValidation
    private String phoneNumber;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;


}
