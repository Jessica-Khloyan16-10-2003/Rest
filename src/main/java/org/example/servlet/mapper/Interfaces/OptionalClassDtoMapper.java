package org.example.servlet.mapper.Interfaces;

import org.example.entity.OptionalClass;
import org.example.servlet.dto.OptionalClassIncomingDto;
import org.example.servlet.dto.OptionalClassOutGoingDto;
import org.example.servlet.dto.OptionalClassUpdateDto;

import java.util.List;

public interface OptionalClassDtoMapper {
    OptionalClass map(OptionalClassIncomingDto optionalClassIncomingDto);

    OptionalClassOutGoingDto map(OptionalClass optionalClass);

    OptionalClass map(OptionalClassUpdateDto optionalClassUpdateDto);

    List<OptionalClassOutGoingDto> map(List<OptionalClass> optionalClassList);

    List<OptionalClass> mapUpdateList(List<OptionalClassUpdateDto> optionalClassList);
}
