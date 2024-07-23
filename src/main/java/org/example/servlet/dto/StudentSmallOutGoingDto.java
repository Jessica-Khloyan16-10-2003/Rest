package org.example.servlet.dto;

public class StudentSmallOutGoingDto {
    private Long id;
    private String firstName;
    private String surname;

    public StudentSmallOutGoingDto() {
    }

    public StudentSmallOutGoingDto(Long id, String firstName, String surname) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }
}
