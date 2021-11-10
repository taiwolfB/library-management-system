package org.team4.libraryManagement.model;

import java.util.UUID;

public class Student {

    private String uuid;
    private String firstName;
    private String lastName;
    private boolean blacklisted;
    private String email;

    public Student(String uuid, String firstName, String lastName, boolean blacklisted, String email) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.blacklisted = blacklisted;
        this.email = email;
    }

    public Student() {

    }

    public Student(String uuid) {
        this.uuid = uuid;
    }

    public Student(String uuid, String firstName) {
        this.uuid = uuid;
        this.firstName = firstName;
    }

    public Student(String uuid, String firstName, String lastName) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student(String uuid, String firstName, String lastName, boolean blacklisted) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.blacklisted = blacklisted;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isBlacklisted() {
        return blacklisted;
    }

    public void setBlacklisted(boolean blacklisted) {
        this.blacklisted = blacklisted;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
