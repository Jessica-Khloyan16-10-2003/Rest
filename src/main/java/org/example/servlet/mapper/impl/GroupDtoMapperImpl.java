package org.example.servlet.mapper.impl;

import org.example.entity.Group;
import org.example.servlet.dto.GroupIncomingDto;
import org.example.servlet.dto.GroupOutGoingDto;
import org.example.servlet.dto.GroupUpdateDto;
import org.example.servlet.mapper.Interfaces.GroupDtoMapper;

import java.util.List;

public class GroupDtoMapperImpl implements GroupDtoMapper {
    private static GroupDtoMapper instance;

    private GroupDtoMapperImpl() {
    }

    public static synchronized GroupDtoMapper getInstance() {
        if (instance == null) {
            instance = new GroupDtoMapperImpl();
        }
        return instance;
    }

    @Override
    public Group map(GroupIncomingDto groupIncomingDto) {
        return new Group(
                null,
                groupIncomingDto.getName()
        );
    }

    @Override
    public Group map(GroupUpdateDto groupUpdateDto) {
        return new Group(
                groupUpdateDto.getId(),
                groupUpdateDto.getName());
    }

    @Override
    public GroupOutGoingDto map(Group group) {
        return new GroupOutGoingDto(
                group.getId(),
                group.getName()
        );
    }

    @Override
    public List<GroupOutGoingDto> map(List<Group> groupList) {
        return groupList.stream().map(this::map).toList();
    }
}
