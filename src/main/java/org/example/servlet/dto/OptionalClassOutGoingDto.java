package org.example.servlet.dto;

import java.util.List;

public class OptionalClassOutGoingDto {
    private Long id;
    private String name;
    private List<StudentSmallOutGoingDto> studentList;

    public OptionalClassOutGoingDto() {
    }

    public OptionalClassOutGoingDto(Long id, String name, List<StudentSmallOutGoingDto> studentList) {
        this.id = id;
        this.name = name;
        this.studentList = studentList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StudentSmallOutGoingDto> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<StudentSmallOutGoingDto> studentList) {
        this.studentList = studentList;
    }
}
