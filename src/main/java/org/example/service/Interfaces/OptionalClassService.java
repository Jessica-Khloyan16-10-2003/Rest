package org.example.service.Interfaces;

import org.example.servlet.dto.OptionalClassIncomingDto;
import org.example.servlet.dto.OptionalClassOutGoingDto;
import org.example.servlet.dto.OptionalClassUpdateDto;


public interface OptionalClassService {
    OptionalClassOutGoingDto save(OptionalClassIncomingDto department);

    void update(OptionalClassUpdateDto department) throws RuntimeException;

    OptionalClassOutGoingDto findById(Long departmentId) throws RuntimeException;

    void delete(Long departmentId) throws RuntimeException;
}
