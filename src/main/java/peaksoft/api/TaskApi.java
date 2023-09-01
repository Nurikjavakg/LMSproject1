package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.dto.task.TaskRequest;
import peaksoft.dto.task.TaskResponse;
import peaksoft.services.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskApi {

    private final TaskService taskService;


    @GetMapping("/{lessonId}")
    public List<TaskResponse> getAllTasks(@PathVariable Long lessonId) {
        return taskService.getAllTask(lessonId);
    }


    @PostMapping("/{lessonId}")
    public SimpleResponse saveCourse(@PathVariable Long lessonId, @RequestBody TaskRequest taskRequest) {
        taskService.saveTask(lessonId, taskRequest);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Task is saved")
                .build();
    }

    @GetMapping("/get/{taskId}")
    public TaskResponse getCourseById(@PathVariable Long taskId) {
        return taskService.getTaskId(taskId);
    }


    @PutMapping("/{lessonId}/{taskId}")
    public SimpleResponse updateCompany(@PathVariable Long lessonId, @PathVariable Long taskId, @RequestBody TaskRequest taskRequest) {
        taskService.updateTask(lessonId, taskId, taskRequest);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Task is updated...")
                .build();
    }

    @DeleteMapping("/{lessonId}/{taskId}")
    public SimpleResponse deleteTask(@PathVariable Long lessonId, @PathVariable Long taskId){
        taskService.deleteTask(lessonId,taskId);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Task is deleted...")
                .build();
    }
    }
