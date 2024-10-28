package com.tyntec.todo.controller;

import com.tyntec.todo.model.CreateTaskRequest;
import com.tyntec.todo.model.Task;
import com.tyntec.todo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
        return taskService.saveTask(request);
    }

    @PatchMapping("/{id}")
    public Task updateTaskStatus(@PathVariable Long id,  @RequestBody Map<String, Boolean> updates) {
        return taskService.updateTask(id, updates);
    }

    @GetMapping("/completedTasks")
    public List<Task> getCompletedTasks() {
        return taskService.getAllTasks(true);
    }
}
