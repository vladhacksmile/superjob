package com.vladhacksmile.searchjob.enums;

public enum UserRole {
    APPLICANT("APPLICANT"), EMPLOYER("EMPLOYER");
    private final String name;

    UserRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
