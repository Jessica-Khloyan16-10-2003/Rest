package org.example.servlet.mapper.Interfaces;

import org.example.entity.Student;
import org.example.servlet.dto.StudentIncomingDto;
import org.example.servlet.dto.StudentOutGoingDto;
import org.example.servlet.dto.StudentUpdateDto;

import java.util.List;

public interface StudentDtoMapper {
    Student map(StudentIncomingDto studentIncomingDto);

    Student map(StudentUpdateDto studentIncomingDto);

    StudentOutGoingDto map(Student student);

    List<StudentOutGoingDto> map(List<Student> student);

}
