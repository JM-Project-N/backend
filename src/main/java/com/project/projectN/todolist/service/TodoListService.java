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

    /**
     * ToDoList 추가하는 메서드
     * @param todoList
     */
    public void addedTodoListItem(TodoList todoList) {
        Member member = extractMemberFromPrincipal(memberRepository);
        repository.save(todoList);
    }

    /**
     * TodoList의 ID를 받아와 존재하는지 여부를 파악 후 수정.
     * @param todoList
     * @return
     */
    public TodoList patchTodoListItem(TodoList todoList) {
        Member member = extractMemberFromPrincipal(memberRepository);
        TodoList findTodoList = repository.findById(todoList.getTodoListId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.TODOLIST_NOT_FOUND));
        if(!findTodoList.getTeamId().equals(todoList.getTeamId()))
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND); // 수정 필요
        return repository.save(todoList);
    }

    public void deleteTodoListItem(Long todoListId) {
        Member member = extractMemberFromPrincipal(memberRepository);
        TodoList findTodoList = repository.findByTodoListIdAndCreatedBy(todoListId, member.getEmail())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.TODOLIST_NOT_FOUND));
        repository.delete(findTodoList);
    }

    public List<TodoList> getTodoListPage(String filter, String teamId){
        Member member = extractMemberFromPrincipal(memberRepository);
        Sort sortBy = Sort.by("todoListId").ascending();
        List<TodoList> result;
        switch (filter){
            case "my":
                result = repository
                        .findByTeamIdAndCreatedBy(teamId,member.getEmail(), sortBy); // 금일 유효 판단 필요
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
