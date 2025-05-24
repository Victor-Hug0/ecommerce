package com.project.ecommerce.models.shared;

public enum UserRole {
    CUSTOMER("customer"),
    ;

    private final String userRole;

    UserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserRole() {
        return userRole;
    }
}
