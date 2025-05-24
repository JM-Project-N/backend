package com.project.projectN.todolist.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "todolist")
public class TodoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todolist_id")
    private Long todoListId;

    @Column(name = "team_id", length = 50)
    private String teamId;

    @Column(name = "email", length = 30)
    private String email;

    @Column(name = "idx", nullable = false)
    private Long idx;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "repeat_type", length = 30)
    private String repeatType;

    @Column(name = "repeat_description", length = 100)
    private String repeatDescription;

    @Column(name = "done")
    private Boolean done;

    @Column(name = "public_todo")
    private Boolean publicTodo;
}
