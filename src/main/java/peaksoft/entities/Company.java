package peaksoft.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String country;
    private String address;
    private Long phoneNumber;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<Instructor> instructors;
    @OneToMany(mappedBy = "company", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<Course>courseList;

}