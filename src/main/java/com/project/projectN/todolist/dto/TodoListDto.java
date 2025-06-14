package com.project.projectN.todolist.dto;

import lombok.*;

public class TodoListDto {

    @Data
    @AllArgsConstructor
    public static class GetTodoInfo {
        private Long todoListId;
        private String title;
        private String repeatType;
        private String repeatDescription;
        private String done;
        private Boolean publicTodo;
    }

    @Data
    @AllArgsConstructor
    public static class SetTodoInfo {
        private String teamId;
        private String title;
        private String repeatType;
        private String repeatDescription;
        private String done;
        private Boolean publicTodo;
    }

    @Data
    @AllArgsConstructor
    public static class PatchTodoInfo {
        private Long todoListId;
        private String title;
        private String repeatType;
        private String repeatDescription;
        private String done;
        private Boolean publicTodo;
    }

}