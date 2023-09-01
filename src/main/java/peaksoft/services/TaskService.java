package peaksoft.services;

import peaksoft.dto.lesson.LessonRequest;
import peaksoft.dto.lesson.LessonResponse;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.dto.task.TaskRequest;
import peaksoft.dto.task.TaskResponse;

import java.util.List;

public interface TaskService {
    SimpleResponse saveTask(Long lessonId, TaskRequest taskRequest);
    List<TaskResponse> getAllTask(Long lessonId);
    TaskResponse getTaskId(Long taskId);
    SimpleResponse updateTask(Long lessonId,Long taskId, TaskRequest taskRequest);
    SimpleResponse deleteTask(Long lessonId,Long taskId);
}
