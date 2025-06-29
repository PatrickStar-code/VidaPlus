package com.Uninter.VidaPlus.Exceptions;

public class CpfInvalidException extends BussinesRuleException{
    public CpfInvalidException(String code, String message) {
        super(code, message);
    }
}
