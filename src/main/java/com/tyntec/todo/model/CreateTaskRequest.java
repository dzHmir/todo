package com.tyntec.todo.model;

import java.time.LocalDate;

public record CreateTaskRequest(String name, LocalDate deadline) { }
