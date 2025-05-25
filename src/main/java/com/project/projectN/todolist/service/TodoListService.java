package com.project.projectN.todolist.service;


import com.project.projectN.auth.service.ExtractMemberAndVerify;
import com.project.projectN.exception.BusinessLogicException;
import com.project.projectN.exception.ExceptionCode;
import com.project.projectN.member.entity.Member;
import com.project.projectN.member.repository.MemberRepository;
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

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class TodoListService extends ExtractMemberAndVerify {
    private final TodoListRepository repository;
    private final MemberRepository memberRepository;

    public void addedTodoListItem(TodoList todoList) {
        Member member = extractMemberFromPrincipal(memberRepository);
        repository.save(todoList);
    }

    public TodoList patchTodoListItem(TodoList todoList) {
        Member member = extractMemberFromPrincipal(memberRepository);
        TodoList findTodoList = repository.findById(todoList.getTodoListId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.TODOLIST_NOT_FOUND));
        return repository.save(todoList);
    }

    public void deleteTodoListItem(Long todoListId) {
        Member member = extractMemberFromPrincipal(memberRepository);
        TodoList findTodoList = repository.findByTodoListIdAndEmail(todoListId, member.getEmail())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.TODOLIST_NOT_FOUND));
        repository.delete(findTodoList);
    }

    public List<TodoList> getTodoListPage(String filter, String teamId){
        Member member = extractMemberFromPrincipal(memberRepository);
        Sort sortBy = Sort.by("idx").ascending();
        List<TodoList> result;
        switch (filter){
            case "my":
                result = repository
                        .findByTeamIdAndEmail(teamId,member.getEmail(), sortBy); // 금일 유효 판단 필요
                break;
            case "public":
                result = repository
                        .findByTeamIdAndPublicTodo(teamId,true, sortBy); // 금일 유효 판단 필요
                break;
            default:
                throw new BusinessLogicException(ExceptionCode.BATTERY_CODE_NOT_FOUND); //수정필요
        }
        return result;
    }
}
