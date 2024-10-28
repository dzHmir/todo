package com.tyntec.todo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Builder
@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate deadline;

    @Builder.Default
    private Boolean isCompleted = false;

}
