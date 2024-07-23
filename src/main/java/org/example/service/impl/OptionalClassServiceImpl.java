package org.example.service.impl;

import org.example.entity.OptionalClass;
import org.example.repository.Interfaces.OptionalClassRepository;
import org.example.repository.impl.OptionalClassRepositoryImpl;
import org.example.service.Interfaces.OptionalClassService;
import org.example.servlet.dto.OptionalClassIncomingDto;
import org.example.servlet.dto.OptionalClassOutGoingDto;
import org.example.servlet.dto.OptionalClassUpdateDto;
import org.example.servlet.mapper.Interfaces.OptionalClassDtoMapper;
import org.example.servlet.mapper.impl.OptionalClassDtoMapperImpl;


public class OptionalClassServiceImpl implements OptionalClassService {
    private final OptionalClassRepository departmentRepository = OptionalClassRepositoryImpl.getInstance();
    private static final OptionalClassDtoMapper departmentDtoMapper = OptionalClassDtoMapperImpl.getInstance();
    private static OptionalClassService instance;


    private OptionalClassServiceImpl() {
    }

    public static synchronized OptionalClassService getInstance() {
        if (instance == null) {
            instance = new OptionalClassServiceImpl();
        }
        return instance;
    }

    private void checkExistDepartment(Long departmentId) throws RuntimeException {
        if (!departmentRepository.exitsById(departmentId)) {
            throw new RuntimeException("Department not found.");
        }
    }

    @Override
    public OptionalClassOutGoingDto save(OptionalClassIncomingDto departmentDto) {
        OptionalClass department = departmentDtoMapper.map(departmentDto);
        department = departmentRepository.insert(department);
        return departmentDtoMapper.map(department);
    }

    @Override
    public void update(OptionalClassUpdateDto departmentUpdateDto) throws RuntimeException {
        checkExistDepartment(departmentUpdateDto.getId());
        OptionalClass department = departmentDtoMapper.map(departmentUpdateDto);
        departmentRepository.update(department);
    }

    @Override
    public OptionalClassOutGoingDto findById(Long departmentId) throws RuntimeException {
        OptionalClass department = departmentRepository.findById(departmentId).orElseThrow(() ->
                new RuntimeException("Department not found."));
        return departmentDtoMapper.map(department);
    }

    @Override
    public void delete(Long departmentId) throws RuntimeException {
        checkExistDepartment(departmentId);
        departmentRepository.deleteById(departmentId);
    }

}
