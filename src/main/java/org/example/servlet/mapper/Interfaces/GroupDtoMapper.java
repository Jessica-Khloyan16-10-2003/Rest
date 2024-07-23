package org.example.servlet.mapper.Interfaces;

import org.example.entity.Group;
import org.example.servlet.dto.GroupIncomingDto;
import org.example.servlet.dto.GroupOutGoingDto;
import org.example.servlet.dto.GroupUpdateDto;

import java.util.List;

public interface GroupDtoMapper {
    Group map(GroupIncomingDto groupIncomingDto);

    Group map(GroupUpdateDto groupUpdateDto);

    GroupOutGoingDto map(Group group);

    List<GroupOutGoingDto> map(List<Group> groupList);
}
