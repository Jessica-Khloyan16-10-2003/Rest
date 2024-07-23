package org.example.servlet.dto;

public class OptionalClassUpdateDto {
    private Long id;
    private String name;

    public OptionalClassUpdateDto() {
    }

    public OptionalClassUpdateDto(Long id, String name) {
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
