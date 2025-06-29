package com.Uninter.VidaPlus.Exceptions;

public class InvalidArgumentException extends BussinesRuleException {
  public InvalidArgumentException(String code, String message) {
    super(code, message);
  }
}
