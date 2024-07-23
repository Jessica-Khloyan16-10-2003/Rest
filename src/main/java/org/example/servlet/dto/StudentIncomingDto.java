package org.example.servlet.dto;

import org.example.entity.Group;

public class StudentIncomingDto {
    private String firstName;
    private String surname;

    private Group group;

    public StudentIncomingDto() {
    }

    public StudentIncomingDto(String firstName, String surname, Group group) {
        this.firstName = firstName;
        this.surname = surname;
        this.group = group;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public Group getGroup() {
        return group;
    }

}

