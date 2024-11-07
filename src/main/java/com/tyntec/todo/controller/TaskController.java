package com.tyntec.todo.controller;

import com.tyntec.todo.model.CreateTaskRequest;
import com.tyntec.todo.model.TaskRecorder;
import com.tyntec.todo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/allTasks")
    public List<TaskRecorder> getAllTasks(@RequestParam boolean isCompleted) {
        return taskService.getAllTasks(isCompleted);
    }

    @PostMapping
    public TaskRecorder createTask(@RequestBody CreateTaskRequest request) {
        return taskService.saveTask(request);
    }

    @PatchMapping("/{id}")
    public TaskRecorder updateTaskStatus(@PathVariable Long id,  @RequestBody Map<String, Boolean> updates) {
        return taskService.updateTask(id, updates);
    }

    @GetMapping("/completedTasks")
    public List<TaskRecorder> getCompletedTasks() {
        return taskService.getAllTasks(true);
    }
}
