package peaksoft.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import peaksoft.enums.Role;

@Entity
@Table(name = "student")
@Getter
@Setter
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String firstName;
    private String lastName;
    private Long phoneNumber;
    private String email;
    private String password;
    private String studyFormat;
    private Role role;
    @Column(name = "is_blocked")
    private Boolean isBlocked;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private Group group;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;


}