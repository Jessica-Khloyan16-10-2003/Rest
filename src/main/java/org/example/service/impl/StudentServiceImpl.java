package org.example.service.impl;

import org.example.entity.Student;
import org.example.repository.Interfaces.StudentRepository;
import org.example.repository.impl.StudentRepositoryImpl;
import org.example.service.Interfaces.StudentService;
import org.example.servlet.dto.StudentIncomingDto;
import org.example.servlet.dto.StudentOutGoingDto;
import org.example.servlet.dto.StudentUpdateDto;
import org.example.servlet.mapper.Interfaces.StudentDtoMapper;
import org.example.servlet.mapper.impl.StudentDtoMapperImpl;

public class StudentServiceImpl implements StudentService {
    private final StudentRepository userRepository = StudentRepositoryImpl.getInstance();
    private static final StudentDtoMapper userDtoMapper = StudentDtoMapperImpl.getInstance();
    private static StudentService instance;


    private StudentServiceImpl() {
    }

    public static synchronized StudentService getInstance() {
        if (instance == null) {
            instance = new StudentServiceImpl();
        }
        return instance;
    }

    private void checkExistUser(Long userId) throws RuntimeException {
        if (!userRepository.exitsById(userId)) {
            throw new RuntimeException("User not found.");
        }
    }

    @Override
    public StudentOutGoingDto save(StudentIncomingDto userDto) {
        Student user = userRepository.insert(userDtoMapper.map(userDto));
        return userDtoMapper.map(userRepository.findById(user.getId()).orElse(user));
    }

    @Override
    public void update(StudentUpdateDto userDto) throws RuntimeException {
        if (userDto == null || userDto.getId() == null) {
            throw new IllegalArgumentException();
        }
        checkExistUser(userDto.getId());
        userRepository.update(userDtoMapper.map(userDto));
    }

    @Override
    public StudentOutGoingDto findById(Long userId) throws RuntimeException {
        checkExistUser(userId);
        Student user = userRepository.findById(userId).orElseThrow();
        return userDtoMapper.map(user);
    }

    @Override
    public void delete(Long userId) throws RuntimeException {
        checkExistUser(userId);
        userRepository.deleteById(userId);
    }
}
