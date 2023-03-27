package com.szxs.exception;

/**
 * 登陆异常
 */
public class LoginException extends DmwException{
    public LoginException() {
    }

    public LoginException(String message) {
        super(message);
    }
}
