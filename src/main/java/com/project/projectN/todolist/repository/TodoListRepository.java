package com.project.projectN.todolist.repository;

import com.project.projectN.todolist.entity.TodoList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {
    Page<TodoList> findAllTeamIdAndEmail(String teamId, String email, Pageable pageable);
    Page<TodoList> findAllTeamIdAndPublicTodo(String teamId, Boolean publicTodo, Pageable pageable);
    Page<TodoList> findByTeamIdAndEmailOrTeamIdAndIsPublic(String teamId1, String email, String teamId2, boolean isPublic, Pageable pageable);
}
