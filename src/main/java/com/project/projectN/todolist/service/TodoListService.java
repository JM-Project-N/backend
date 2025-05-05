package com.project.projectN.todolist.service;


import com.project.projectN.todolist.entity.TodoList;
import com.project.projectN.todolist.repository.TodoListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class TodoListService {
    private final TodoListRepository repository;

    public Page<TodoList> getTodoListPage(String token, String filter, Pageable pageable){
        Sort sortBy = Sort.by("idx").ascending();
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sortBy);
        Page<TodoList> result;
        switch (filter){
            case "my":
                result = repository.findAllEmail();
                break;
            case "all":
                result = repository.findAllTeamId();
                break;
            default:

        }
        return result;
    }
}
