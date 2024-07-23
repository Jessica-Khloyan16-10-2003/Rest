package org.example.entity;

import java.util.List;

public class Student {
    private Long id;
    private String firstName;
    private String surname;
    private Group group;
    private List<OptionalClass> optionalClassList;

    public Student() {
    }

    public Student(Long id, String firstName, String lastName, Group group, List<OptionalClass> optionalClassList) {
        this.id = id;
        this.firstName = firstName;
        this.surname = lastName;
        this.group = group;
        this.optionalClassList = optionalClassList;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }


    public List<OptionalClass> getOptionalClassList() {
        return optionalClassList;
    }

    public void setOptionalClassList(List<OptionalClass> optionalClassList) {
        this.optionalClassList = optionalClassList;
    }
}
