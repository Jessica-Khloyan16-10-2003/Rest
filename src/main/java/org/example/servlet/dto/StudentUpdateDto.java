package org.example.servlet.dto;

import java.util.List;

public class StudentUpdateDto {
    private Long id;
    private String firstName;
    private String surname;
    private GroupUpdateDto group;
    private List<OptionalClassUpdateDto> optionalClassList;

    public StudentUpdateDto() {
    }

    public StudentUpdateDto(Long id, String firstName, String surname, GroupUpdateDto group, List<OptionalClassUpdateDto> optionalClassList) {
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

    public GroupUpdateDto getGroup() {
        return group;
    }

    public List<OptionalClassUpdateDto> getOptionalClassList() {
        return optionalClassList;
    }

}

