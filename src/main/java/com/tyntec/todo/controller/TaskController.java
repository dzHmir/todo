package com.tyntec.todo.controller;

import com.tyntec.todo.model.CreateTaskRequest;
import com.tyntec.todo.model.Task;
import com.tyntec.todo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public Task createTask(@RequestBody CreateTaskRequest request) {
        Task task = Task.builder().name(request.name()).deadline(request.deadline()).build();
        return taskService.saveTask(task);
    }

    @GetMapping("/force")
    public Task forceCreateTask(@RequestParam String taskName) {
        Task task = Task.builder().name(taskName).build();
        return taskService.saveTask(task);
    }
}
