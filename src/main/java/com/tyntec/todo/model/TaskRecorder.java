package com.tyntec.todo.model;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TaskRecorder {
    private Long id;
    private String name;
    private LocalDate deadline;

    @Builder.Default
    private Boolean isCompleted = false;
}
