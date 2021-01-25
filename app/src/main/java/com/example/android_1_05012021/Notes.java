package com.example.android_1_05012021;

public class Notes {
    private String name;
    private String description;
    private String comment;
    private String status;
    private String dateOfCreation;

    public Notes() {
    }

    public Notes(String name, String description, String comment, String status, String dateOfCreation) {
        this.name = name;
        this.description = description;
        this.comment = comment;
        this.status = status;
        this.dateOfCreation = dateOfCreation;
    }
}
