package com.Uninter.VidaPlus.Exceptions;

public class PasswordConfirmIncorrectException extends BussinesRuleException {
    public PasswordConfirmIncorrectException(String code, String message) {
        super(code, message);
    }
}
