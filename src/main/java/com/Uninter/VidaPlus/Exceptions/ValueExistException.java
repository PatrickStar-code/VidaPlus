package com.Uninter.VidaPlus.Exceptions;

public class ValueExistException extends  BussinesRuleException {
    public ValueExistException(String code, String message) {
        super(code, message);
    }
}
