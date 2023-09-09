package peaksoft.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.MERGE;
@Entity
@Table(name = "instructors")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Instructor {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "instructor_gen")
    @SequenceGenerator(
            name = "instructor_gen",
            sequenceName = "instructor_seq",
            allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String specialization;
    private LocalDate createdAt;
    @OneToOne(cascade = ALL)
    private User user;
    @ManyToMany(mappedBy = "instructors", cascade = {PERSIST, DETACH, REFRESH, MERGE})
    private List<Company> companies;
    @OneToMany(mappedBy = "instructor", cascade = ALL)
    private List<Course> courses;
}