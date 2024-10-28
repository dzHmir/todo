package com.tyntec.todo.service;

import com.tyntec.todo.model.CreateTaskRequest;
import com.tyntec.todo.model.Task;
import com.tyntec.todo.model.TaskRecorder;
import com.tyntec.todo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<TaskRecorder> getAllTasks(boolean isComplete) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getIsCompleted() == isComplete)
                .map(this::toTaskRecorder).collect(Collectors.toList());
    }

    public TaskRecorder updateTask(Long id, Map<String, Boolean> values) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            Boolean isCompleted = values.get("isCompleted");
            task.setIsCompleted(isCompleted);
            return toTaskRecorder(taskRepository.save(task));
        }
        return null;
    }

    public TaskRecorder saveTask(CreateTaskRequest request) {
        TaskRecorder task = TaskRecorder.builder()
                .name(request.name())
                .deadline(request.deadline())
                .isCompleted(false)
                .build();
        return toTaskRecorder(taskRepository.save(toTask(task)));
    }

    public TaskRecorder toTaskRecorder(Task task) {
        return TaskRecorder.builder().id(task.getId())
                .deadline(task.getDeadline())
                .name(task.getName())
                .build();
    }

    public Task toTask(TaskRecorder taskRecorder) {
        return Task.builder()
                .id(taskRecorder.getId())
                .deadline(taskRecorder.getDeadline())
                .name(taskRecorder.getName())
                .build();
    }

}
