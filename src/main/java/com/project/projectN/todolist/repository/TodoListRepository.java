package com.project.projectN.todolist.repository;

import com.project.projectN.todolist.entity.TodoList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {
    Optional<TodoList> findByTodoListIdAndEmail(Long todoListId, String email);
    List<TodoList> findByTeamIdAndEmail(String teamId, String email, Sort sort);
    List<TodoList> findByTeamIdAndPublicTodo(String teamId, Boolean publicTodo, Sort sort);
}
