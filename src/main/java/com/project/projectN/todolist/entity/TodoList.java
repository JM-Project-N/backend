package com.project.projectN.todolist.entity;
import com.project.projectN.audit.Auditable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "todolist")
public class TodoList extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todolist_id")
    private Long todoListId;

    @Column(name = "team_id", length = 50)
    private String teamId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "repeat_type", length = 30)
    private String repeatType;

    @Column(name = "repeat_description", length = 100)
    private String repeatDescription;

    @Column(name = "done", length = 30)
    private String done;

    @Column(name = "public_todo")
    private Boolean publicTodo;
}
