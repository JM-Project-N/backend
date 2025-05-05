package com.project.projectN.todolist.controller;

import com.project.projectN.todolist.entity.TodoList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/todolist")
@RequiredArgsConstructor
public class TodoListController {

    @GetMapping("/{token}")
    public ResponseEntity<?> getData(@PathVariable String token, @RequestParam String filter, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page - 1, 50);
        Page<TodoList> =
    }
}
