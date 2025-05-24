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

    public void patchTodoListItem(Long todoListId) {
        Member member = extractMemberFromPrincipal(memberRepository);
        TodoList todoList = repository.findById(todoListId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.TODOLIST_NOT_FOUND));


    }

    public Page<TodoList> getTodoListPage(String filter, Pageable pageable, String teamId){
        Member member = extractMemberFromPrincipal(memberRepository);
        Sort sortBy = Sort.by("idx").ascending();
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sortBy);
        Page<TodoList> result;
        switch (filter){
            case "my":
                result = repository
                        .findAllTeamIdAndEmail(teamId,member.getEmail(),pageable);
                break;
            case "public":
                result = repository
                        .findAllTeamIdAndPublicTodo(teamId,true,pageable);
                break;
            default: //all
                result = repository
                        .findByTeamIdAndEmailOrTeamIdAndIsPublic(teamId, member.getEmail(), teamId, true, pageable);
        }
        return result;
    }
}
