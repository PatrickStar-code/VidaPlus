package com.Uninter.VidaPlus.Controller.Request;

public record UpdatePassword(
    String newPassword,
    String confirmPassword
) {
}
