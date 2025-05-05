package com.project.projectN.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoListDto {

    private Long todoListId;
    private String teamId;
    private String email;
    private Long idx;
    private String title;
    private String repeatType;
    private String repeatDescription;
    private Boolean done;
    private Boolean privateView;
}