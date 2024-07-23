package org.example.servlet.dto;

public class OptionalClassIncomingDto {
    private String name;

    public OptionalClassIncomingDto() {
    }

    public OptionalClassIncomingDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
