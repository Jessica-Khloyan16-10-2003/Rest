package org.example.service.impl;

import org.example.entity.Group;
import org.example.repository.Interfaces.GroupRepository;
import org.example.repository.impl.GroupRepositoryImpl;
import org.example.service.Interfaces.GroupService;
import org.example.servlet.dto.GroupIncomingDto;
import org.example.servlet.dto.GroupOutGoingDto;
import org.example.servlet.dto.GroupUpdateDto;
import org.example.servlet.mapper.Interfaces.GroupDtoMapper;
import org.example.servlet.mapper.impl.GroupDtoMapperImpl;

public class GroupServiceImpl implements GroupService {
    private GroupRepository groupRepository = GroupRepositoryImpl.getInstance();
    private static GroupService instance;
    private final GroupDtoMapper roleDtoMapper = GroupDtoMapperImpl.getInstance();


    private GroupServiceImpl() {
    }

    public static synchronized GroupService getInstance() {
        if (instance == null) {
            instance = new GroupServiceImpl();
        }
        return instance;
    }

    @Override
    public GroupOutGoingDto save(GroupIncomingDto roleDto) {
        Group role = roleDtoMapper.map(roleDto);
        role = groupRepository.insert(role);
        return roleDtoMapper.map(role);
    }

    @Override
    public void update(GroupUpdateDto roleUpdateDto) throws RuntimeException {
        checkRoleExist(roleUpdateDto.getId());
        Group role = roleDtoMapper.map(roleUpdateDto);
        groupRepository.update(role);
    }

    @Override
    public GroupOutGoingDto findById(Long roleId) throws RuntimeException {
        Group role = groupRepository.findById(roleId).orElseThrow(() ->
                new RuntimeException("Role not found."));
        return roleDtoMapper.map(role);
    }

    @Override
    public boolean delete(Long roleId) throws RuntimeException {
        checkRoleExist(roleId);
        return groupRepository.deleteById(roleId);
    }

    private void checkRoleExist(Long roleId) throws RuntimeException {
        if (!groupRepository.exitsById(roleId)) {
            throw new RuntimeException("Role not found.");
        }
    }
}
