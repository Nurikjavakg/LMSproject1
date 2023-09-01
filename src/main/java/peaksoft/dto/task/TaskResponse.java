package peaksoft.dto.task;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class TaskResponse {

    private Long id;
    private String taskName;
    private String taskText;
    private LocalDateTime localDateTime;

    public TaskResponse(Long id, String taskName, String taskText, LocalDateTime localDateTime) {
        this.id = id;
        this.taskName = taskName;
        this.taskText = taskText;
        this.localDateTime = localDateTime;
    }
}
