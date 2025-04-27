package com.project.projectN.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCode {

    //workScheduleStep
    WORK_SCHEDULE_STEP_NOT_FOUND(404,"해당 배터리 검사 단계 정보를 찾을 수 없습니다."),

    //BatteryWork
    BATTERY_WORKNAME_IS_DUPLICATED(204,"중복된 WorkName 입니다."),
    BATTERY_WORKNAME_NOT_FOUND(404,"해당 배터리 검사정보를 찾을 수 없습니다."),


    //BatteryCode
    BATTERY_CODE_NOT_FOUND(404,"해당 배터리 정보를 찾을 수 없습니다.");

    private int statusCode;

    private String statusDescription;
}
