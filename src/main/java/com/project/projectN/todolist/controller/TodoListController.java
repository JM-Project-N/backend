package com.project.projectN.todolist.controller;

import com.project.projectN.dto.MultiResponseDto;
import com.project.projectN.dto.SingleResponseDto;
import com.project.projectN.todolist.dto.TodoListDto;
import com.project.projectN.todolist.entity.TodoList;
import com.project.projectN.todolist.mapper.TodoListMapper;
import com.project.projectN.todolist.service.TodoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/todolist")
@RequiredArgsConstructor
public class TodoListController {
    private final TodoListService service;
    private final TodoListMapper mapper;

    /**
     * TodoList 정보를 확인하기 위한 메서드
     * @param teamId 자신이 속해 있는 teamId를 받아 소속되어있는 사람이 맞는지 확인하기 위함.
     * @param filter 공공의 TodoList인지 개인의 TodoList인지 필터링해서 확인
     * @return TodoList 내용들을 List로 반환
     */
    @GetMapping("/{team_id}")
    public ResponseEntity<?> getData(@PathVariable("team_id") String teamId, @RequestParam String filter) {

        return new ResponseEntity<>(
                new MultiResponseDto<>(service.getTodoListPage(filter,teamId),null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addTodoList(@RequestBody TodoListDto.SetTodoInfo request) {
        service.addedTodoListItem(mapper.TodoListToTodoListDtoSetTodoInfo(request));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity patchTodoList(@RequestBody TodoListDto.PatchTodoInfo request) {

    }
}
