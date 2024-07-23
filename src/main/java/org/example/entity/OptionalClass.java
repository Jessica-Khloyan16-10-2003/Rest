package org.example.entity;

import org.example.repository.Interfaces.StudentToOptionalClassRepository;
import org.example.repository.impl.StudentToOptionalClassRepositoryImpl;

import java.util.List;

public class OptionalClass {
    private Long id;
    private String name;
    private List<Student> studentListList;

    public OptionalClass() {
    }

    public OptionalClass(Long id, String name, List<Student> studentListList) {
        this.id = id;
        this.name = name;
        this.studentListList = studentListList;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudentListList() {
        return studentListList;
    }

    public void setStudentListList(List<Student> studentListList) {
        this.studentListList = studentListList;
    }
}