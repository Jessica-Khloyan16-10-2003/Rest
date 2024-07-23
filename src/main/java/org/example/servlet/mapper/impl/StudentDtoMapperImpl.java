package org.example.servlet.mapper.impl;

import org.example.entity.Student;
import org.example.servlet.dto.StudentIncomingDto;
import org.example.servlet.dto.StudentOutGoingDto;
import org.example.servlet.dto.StudentUpdateDto;
import org.example.servlet.mapper.Interfaces.OptionalClassDtoMapper;
import org.example.servlet.mapper.Interfaces.GroupDtoMapper;
import org.example.servlet.mapper.Interfaces.StudentDtoMapper;

import java.util.List;

public class StudentDtoMapperImpl implements StudentDtoMapper {
    private static final GroupDtoMapper groupDtoMapper = GroupDtoMapperImpl.getInstance();
    private static final OptionalClassDtoMapper optionalClassDtoMapper = OptionalClassDtoMapperImpl.getInstance();


    private static StudentDtoMapper instance;

    private StudentDtoMapperImpl() {
    }

    public static synchronized StudentDtoMapper getInstance() {
        if (instance == null) {
            instance = new StudentDtoMapperImpl();
        }
        return instance;
    }

    @Override
    public Student map(StudentIncomingDto studentDto) {
        return new Student(
                null,
                studentDto.getFirstName(),
                studentDto.getSurname(),
                studentDto.getGroup(),
                null
        );
    }

    @Override
    public Student map(StudentUpdateDto studentDto) {
        return new Student(
                studentDto.getId(),
                studentDto.getFirstName(),
                studentDto.getSurname(),
                groupDtoMapper.map(studentDto.getGroup()),
                optionalClassDtoMapper.mapUpdateList(studentDto.getOptionalClassList())
        );
    }

    @Override
    public StudentOutGoingDto map(Student student) {
        return new StudentOutGoingDto(
                student.getId(),
                student.getFirstName(),
                student.getSurname(),
                groupDtoMapper.map(student.getGroup()),
                optionalClassDtoMapper.map(student.getOptionalClassList())
        );
    }

    @Override
    public List<StudentOutGoingDto> map(List<Student> student) {
        return student.stream().map(this::map).toList();
    }
}
