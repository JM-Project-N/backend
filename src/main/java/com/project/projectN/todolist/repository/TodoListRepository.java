package com.project.projectN.todolist.repository;

import com.project.projectN.todolist.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {
}
