package com.tyntec.todo.controller;

import com.tyntec.todo.model.CreateTaskRequest;
import com.tyntec.todo.model.Task;
import com.tyntec.todo.repository.TaskRepository;
import com.tyntec.todo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/allTasks")
    public List<Task> getAllTasks(@RequestParam boolean isCompleted) {
        return taskService.getAllTasks(isCompleted);
    }

    @PostMapping
    public Task createTask(@RequestBody CreateTaskRequest request) {
        Task task = Task.builder()
                .name(request.name())
                .deadline(request.deadline())
                .isCompleted(false)
                .build();
        return taskService.saveTask(task);
    }

    @GetMapping("/force")
    public Task forceCreateTask(@RequestParam String taskName) {
        Task task = Task.builder().name(taskName).build();
        return taskService.saveTask(task);
    }

    @PatchMapping("/{id}")
    public Task updateTaskStatus(@PathVariable Long id,  @RequestBody Map<String, Boolean> updates) {
        Task task = taskService.findById(id);
        if (task != null) {
            task.setIsCompleted(updates.get("isCompleted"));
            return taskService.saveTask(task);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
    }

    @GetMapping("/completedTasks")
    public List<Task> getCompletedTasks() {
        return taskService.getAllTasks(true); // Предполагается, что этот метод возвращает задачи по статусу
    }
}
