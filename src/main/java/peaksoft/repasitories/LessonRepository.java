package peaksoft.repasitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.lesson.LessonResponse;
import peaksoft.entities.Lesson;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    Lesson findByLessonName(String lessonName);
     @Query("select new peaksoft.dto.lesson.LessonResponse(l.id,l.lessonName)from Lesson l join Course c on c.id = :courseId")
    List<LessonResponse> getLessonsByCourseId(Long courseId);

    LessonResponse getLessonsById(Long lessonId);
}