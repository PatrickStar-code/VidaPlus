package com.Uninter.VidaPlus.Exceptions;

public class EmailExistException extends BussinesRuleException {
    public EmailExistException(String code, String message) {
        super(code, message);
    }
}
