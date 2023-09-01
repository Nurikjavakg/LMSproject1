package peaksoft.repasitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.task.TaskResponse;
import peaksoft.entities.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findByTaskName(String taskName);
    @Query("select new peaksoft.dto.task.TaskResponse(t.id, t.taskName, t.taskText, t.localDateTime) from Task t join Lesson l on l.id = :lessonId")
    List<TaskResponse> getTasksByLessonId(Long lessonId);

    TaskResponse getTasksById(Long taskId);
}