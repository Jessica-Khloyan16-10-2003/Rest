package org.example.service.Interfaces;

import org.example.servlet.dto.StudentIncomingDto;
import org.example.servlet.dto.StudentOutGoingDto;
import org.example.servlet.dto.StudentUpdateDto;

public interface StudentService {
    StudentOutGoingDto save(StudentIncomingDto userDto);

    void update(StudentUpdateDto userDto) throws RuntimeException;

    StudentOutGoingDto findById(Long userId) throws RuntimeException;

    void delete(Long userId) throws RuntimeException;
}
