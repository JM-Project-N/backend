package com.project.projectN.todolist.mapper;

import com.project.projectN.todolist.dto.TodoListDto;
import com.project.projectN.todolist.entity.TodoList;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodoListMapper {
    TodoList TodoListToTodoListDtoSetTodoInfo(TodoListDto.SetTodoInfo el);
}
