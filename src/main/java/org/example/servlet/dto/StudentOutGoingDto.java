package org.example.servlet.dto;

import java.util.List;

public class StudentOutGoingDto {
    private Long id;
    private String firstName;
    private String surname;

    private GroupOutGoingDto group;
    private List<OptionalClassOutGoingDto> optionalClassList;

    public StudentOutGoingDto() {
    }

    public StudentOutGoingDto(Long id, String firstName, String surname, GroupOutGoingDto group, List<OptionalClassOutGoingDto> optionalClassList) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.group = group;
        this.optionalClassList = optionalClassList;
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

    public GroupOutGoingDto getGroup() {
        return group;
    }

    public List<OptionalClassOutGoingDto> getOptionalClassList() {
        return optionalClassList;
    }

}
