package peaksoft.dto.task;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class TaskRequest {
    private String taskName;
    private String taskText;
    private LocalDateTime localDateTime;
}
