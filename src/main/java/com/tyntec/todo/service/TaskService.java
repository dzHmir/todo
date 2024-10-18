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

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task saveTask(Task tasksModel) {
        return taskRepository.save(tasksModel);
    }
    public Task getTaskByName(String name) {
        return taskRepository.findByName(name);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}

