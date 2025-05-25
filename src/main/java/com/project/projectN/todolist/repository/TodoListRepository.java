package com.project.projectN.todolist.repository;

import com.project.projectN.todolist.entity.TodoList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {
    Page<TodoList> findByTeamIdAndEmail(String teamId, String email, Pageable pageable);
    Page<TodoList> findByTeamIdAndPublicTodo(String teamId, Boolean publicTodo, Pageable pageable);
    Page<TodoList> findByTeamIdAndEmailOrTeamIdAndPublicTodo(String teamId1, String email, String teamId2, boolean publicTodo, Pageable pageable);
}
