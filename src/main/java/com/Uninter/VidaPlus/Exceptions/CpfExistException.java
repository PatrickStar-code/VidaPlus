package com.Uninter.VidaPlus.Exceptions;

public class CpfExistException extends BussinesRuleException {
    public CpfExistException(String code, String message) {
        super(code, message);
    }
}
