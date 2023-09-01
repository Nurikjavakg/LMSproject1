package peaksoft.dto.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class CompanyResponse {
    private Long id;
    private String name;
    private String country;
    private String address;
    private Long phoneNumber;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public CompanyResponse(Long id, String name, String country, String address, Long phoneNumber, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
