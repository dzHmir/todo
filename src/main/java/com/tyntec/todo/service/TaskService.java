package com.tyntec.todo.service;

import com.tyntec.todo.model.CreateTaskRequest;
import com.tyntec.todo.model.Task;
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

    public List<Task> getAllTasks(boolean isComplete) {
        return taskRepository.findAll().stream().filter(task -> task.getIsCompleted().equals(isComplete)).toList();
    }

    public Task updateTask(Long id, Map<String, Boolean> values) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setIsCompleted(values.get("isCompleted"));
            return taskRepository.save(task);
        }
        return null;
    }

    public Task saveTask(CreateTaskRequest request) {
        Task task = Task.builder()
                .name(request.name())
                .deadline(request.deadline())
                .isCompleted(false)
                .build();
        return taskRepository.save(task);
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }
}



