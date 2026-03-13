package com.rafael.task_manager.shared.enums;

public enum Role {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_AMIN");

    private  String role;

    Role(String role){
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }
}
