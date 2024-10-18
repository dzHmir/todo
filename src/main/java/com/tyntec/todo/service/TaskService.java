package com.tyntec.todo.service;

import com.tyntec.todo.model.Task;
import com.tyntec.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(boolean isComplete) {
        return taskRepository.findAll().stream().filter(task -> task.getIsCompleted().equals(isComplete)).toList();
    }

    public Task saveTask(Task tasksModel) {
        return taskRepository.save(tasksModel);
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }
}



