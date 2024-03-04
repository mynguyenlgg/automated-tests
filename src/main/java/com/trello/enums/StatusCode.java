package com.trello.enums;

import lombok.Getter;

@Getter
public enum StatusCode {
    CODE_200(200, "OK"),
    CODE_201(201, "Created"),
    CODE_400(400, "Bad Request"),
    CODE_401(401, "Unauthorized"),
    CODE_500(500, "Internal Server Error");

    private final int code;
    private final String msg;

    StatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
