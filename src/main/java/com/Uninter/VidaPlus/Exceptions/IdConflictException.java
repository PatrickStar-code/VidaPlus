package com.Uninter.VidaPlus.Exceptions;

public class IdConflictException  extends BussinesRuleException {

    public IdConflictException(String code, String message) {
        super(code, message);
    }
}
