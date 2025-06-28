package com.Uninter.VidaPlus.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BussinesRuleException extends RuntimeException {
    private final String code;
    private final String message;
}
