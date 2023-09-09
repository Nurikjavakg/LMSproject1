package peaksoft.dto.instructor;

import lombok.Getter;

@Getter

public class InstructorResponseGetStudents {
    private Long id;
    private String fullName;
    private int studentCount;

    public InstructorResponseGetStudents(Long id, String fullName) {
        this.id = id;
        this.fullName = fullName;

    }
}
