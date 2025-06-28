package com.Uninter.VidaPlus.Exceptions;

public class NotFoundException extends BussinesRuleException {
    public NotFoundException(String code, String message) {
        super(code, message);
    }
}
