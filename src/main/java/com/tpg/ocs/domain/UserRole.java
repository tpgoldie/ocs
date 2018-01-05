package com.tpg.ocs.domain;

import lombok.Getter;

import java.util.Optional;
import java.util.stream.Stream;

@Getter
public enum UserRole {
    NEW_CUSTOMER_ROLE("NEW_CUSTOMER"), STANDARD_CUSTOMER_ROLE("STANDARD_CUSTOMER");

    private static final String ROLE_PREFIX = "ROLE_";

    public static Optional<UserRole> matchUserRole(String value) {
        return Stream.of(UserRole.values()).filter(ur -> ur.getLabel().matches(value)).findFirst();
    }

    private final String label;

    private final String role;

    UserRole(String label) {

        this.label = label;

        role = String.format("%s%s", ROLE_PREFIX, label);
    }
}
