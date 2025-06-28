package com.Uninter.VidaPlus.Enums;

public enum RolesEnum {
    USER("user"),
    ADMINISTRADOR("administrador");

    private String role;

    public String getRole() {
        return role;
    }

     RolesEnum(String role) {
        this.role = role;
    }

}
