package com.picpal.framework.core.enums;


public enum ResponseCode {

    // 성공 응답
    SUCCESS("200", "SUCCESS", "The request has succeeded."),

    // 클라이언트 오류 응답
    BAD_REQUEST("400", "BAD_REQUEST", "The request could not be understood by the server due to malformed syntax."),
    UNAUTHORIZED("401", "UNAUTHORIZED", "The request requires user authentication."),
    FORBIDDEN("403", "FORBIDDEN", "The server understood the request, but refuses to authorize it."),
    NOT_FOUND("404", "NOT_FOUND", "The server has not found anything matching the Request-URI."),


    // 서버 오류 응답
    INTERNAL_SERVER_ERROR("500", "INTERNAL_SERVER_ERROR", "The server encountered an unexpected condition which prevented it from fulfilling the request."),
    SERVICE_UNAVAILABLE("503", "SERVICE_UNAVAILABLE", "The server is currently unable to handle the request due to temporary overloading or maintenance of the server."),


    // 사용자 정의 응답 코드
    // 0000 : success
    SUCCESS_RESPONSE("0000", "VALIDATION_ERROR", "The request data is not valid."),

    // 1xxx : validataion
    VALIDATION_ERROR("E1000", "VALIDATION_ERROR", "The request data is not valid."),

    // 2xxx : System error
    DATA_NOT_FOUND("E2000", "DATA_NOT_FOUND", "Requested data is not found."),

    // 3xxx : Network error
    OPERATION_FAILED("E3000", "OPERATION_FAILED", "The operation failed due to some issue."),

    // 4xxx : File Processiong
    FILE_UPLOAD_FAILED("E4000", "FILE_FAILED", "The operation failed due to some issue."),

    // 5xxx : Authentication Error
    AUTH_FAIL_ERROR("E5000", "AUTH_FAILED", "The operation failed due to some issue."),

    // 9999 : Unknow error
    UNKNOW_ERROR("E9999", "FILE_FAILED", "The operation failed due to some issue.");

    private final String code;
    private final String status;
    private final String message;

    ResponseCode(String code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
