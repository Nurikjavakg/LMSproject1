package peaksoft.services;

import peaksoft.dto.group.GroupRequest;
import peaksoft.dto.group.GroupResponse;
import peaksoft.dto.lesson.LessonRequest;
import peaksoft.dto.lesson.LessonResponse;
import peaksoft.dto.simple.SimpleResponse;

import java.util.List;

public interface LessonService {
    SimpleResponse saveLesson(Long courseId, LessonRequest lessonRequest);
    List<LessonResponse> getAllGroup(Long courseId);
    LessonResponse getLessonId(Long lessonId);
    SimpleResponse updateLesson(Long courseId,Long lessonId, LessonRequest lessonRequest);
    SimpleResponse deleteLesson(Long courseId,Long lessonId);
}
