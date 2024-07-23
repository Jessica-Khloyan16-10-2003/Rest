package org.example.servlet.mapper.impl;

import org.example.entity.OptionalClass;
import org.example.servlet.dto.OptionalClassIncomingDto;
import org.example.servlet.dto.OptionalClassOutGoingDto;
import org.example.servlet.dto.OptionalClassUpdateDto;
import org.example.servlet.dto.StudentSmallOutGoingDto;
import org.example.servlet.mapper.Interfaces.OptionalClassDtoMapper;

import java.util.List;

public class OptionalClassDtoMapperImpl implements OptionalClassDtoMapper {
    private static OptionalClassDtoMapper instance;

    private OptionalClassDtoMapperImpl() {
    }

    public static synchronized OptionalClassDtoMapper getInstance() {
        if (instance == null) {
            instance = new OptionalClassDtoMapperImpl();
        }
        return instance;
    }

    @Override
    public OptionalClass map(OptionalClassIncomingDto dto) {
        return new OptionalClass(
                null,
                dto.getName(),
                null
        );
    }

    @Override
    public OptionalClassOutGoingDto map(OptionalClass optionalClass) {
        List<StudentSmallOutGoingDto> userList = optionalClass.getStudentListList()
                .stream().map(student -> new StudentSmallOutGoingDto(
                        student.getId(),
                        student.getFirstName(),
                        student.getSurname()
                )).toList();

        return new OptionalClassOutGoingDto(
                optionalClass.getId(),
                optionalClass.getName(),
                userList
        );
    }

    @Override
    public OptionalClass map(OptionalClassUpdateDto updateDto) {
        return new OptionalClass(
                updateDto.getId(),
                updateDto.getName(),
                null
        );
    }

    @Override
    public List<OptionalClassOutGoingDto> map(List<OptionalClass> optionalClassList) {
        return optionalClassList.stream().map(this::map).toList();
    }

    @Override
    public List<OptionalClass> mapUpdateList(List<OptionalClassUpdateDto> optionalClassList) {
        return optionalClassList.stream().map(this::map).toList();
    }
}
