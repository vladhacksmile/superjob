package com.vladhacksmile.searchjob.enums;

public enum ResumeStatus {
    REVIEW("REVIEW"), REJECT("REJECT"), INVITATION("INVITATION");

    private final String name;

    ResumeStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
