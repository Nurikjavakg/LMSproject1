package peaksoft.services.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.course.CourseResponse;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.dto.task.TaskRequest;
import peaksoft.dto.task.TaskResponse;
import peaksoft.entities.Company;
import peaksoft.entities.Course;
import peaksoft.entities.Lesson;
import peaksoft.entities.Task;
import peaksoft.exception.InvalidNameException;
import peaksoft.exception.NotFoundException;
import peaksoft.repasitories.LessonRepository;
import peaksoft.repasitories.TaskRepository;
import peaksoft.services.TaskService;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final LessonRepository lessonRepository;
    @Override
    public SimpleResponse saveTask(Long lessonId, TaskRequest taskRequest) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow( ()-> new NotFoundException("Lesson with id:"+lessonId+"not found..."));
        Task taskName = taskRepository.findByTaskName(taskRequest.getTaskName());
        if(taskName == null){
            Task task = new Task();
            task.setLesson(lesson);
            task.setTaskName(taskRequest.getTaskName());
            task.setTaskText(taskRequest.getTaskText());
            task.setLocalDateTime(taskRequest.getLocalDateTime());
            taskRepository.save(task);

        return  SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Task is saved...")
                .build();
    }else {
        throw new InvalidNameException(String.format("Task with name: %s exists", taskRequest.getTaskName()));
    }
}

    @Override
    public List<TaskResponse> getAllTask(Long lessonId) {
        List<TaskResponse> tasks = taskRepository.getTasksByLessonId(lessonId);

        if (tasks.isEmpty()) {
            throw new NotFoundException("Company not found with id:"+lessonId);
        }return tasks;

    }

    @Override
    public TaskResponse getTaskId(Long taskId) {
            TaskResponse taskResponse = taskRepository.getTasksById(taskId);
            if(taskResponse == null){
                throw new NotFoundException("task with id:"+taskId+" not found...");
            }
            return taskResponse;
        }

    @Override
    public SimpleResponse updateTask(Long lessonId, Long taskId, TaskRequest taskRequest) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow( ()-> new NotFoundException("Not found company with id:"+lessonId));
        Task task = taskRepository.findById(taskId).orElseThrow( ()-> new NotFoundException("Course with id:"+taskId+" not found!" ));
        Lesson lesson1 = lessonRepository.save(lesson);
        task.setLesson(lesson1);
        task.setTaskName(taskRequest.getTaskName());
        task.setTaskText(taskRequest.getTaskText());
        task.setLocalDateTime(taskRequest.getLocalDateTime());
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Task is updated!")
                .build();
    }


    @Override
    public SimpleResponse deleteTask(Long lessonId, Long taskId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow( ()-> new NotFoundException("Lesson with id:"+lessonId+" not found..."));
        Task task = taskRepository.findById(taskId).orElseThrow( ()-> new NotFoundException("Task with id:"+ taskId+ " not found..."));
        lesson.getTaskList().remove(task);
        taskRepository.delete(task);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Task is deleted!")
                .build();
    }
}
