package com.Uninter.VidaPlus.Exceptions;


public class LoginExistException extends BussinesRuleException {
    public LoginExistException(String code, String message) {
        super(code, message);
    }
}
