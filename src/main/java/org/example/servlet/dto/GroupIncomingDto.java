package org.example.servlet.dto;

public class GroupIncomingDto {
    private String name;

    public GroupIncomingDto() {
    }

    public GroupIncomingDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
