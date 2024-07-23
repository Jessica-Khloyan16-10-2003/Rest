package org.example.servlet.dto;

public class GroupOutGoingDto {
    private Long id;
    private String name;

    public GroupOutGoingDto() {
    }

    public GroupOutGoingDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
