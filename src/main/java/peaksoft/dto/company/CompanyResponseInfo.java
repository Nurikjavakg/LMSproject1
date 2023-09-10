package peaksoft.dto.company;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
public class CompanyResponseInfo {
    private Long id;
    private String name;
    private String country;
    private String address;
    private String phoneNumber;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private List<String> courseName;
    private List<String> groupName;
    private List<String> instructorName;
    private int studentCount;

    public CompanyResponseInfo(Long id, String name, String country, String address, String phoneNumber, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
       
    }

    public CompanyResponseInfo(Long id, String name, String country, String address, String phoneNumber, ZonedDateTime createdAt, ZonedDateTime updatedAt, List<String> courseName, List<String> groupName, List<String> instructorName, int studentCount) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.courseName = courseName;
        this.groupName = groupName;
        this.instructorName = instructorName;
        this.studentCount = studentCount;
    }


}
