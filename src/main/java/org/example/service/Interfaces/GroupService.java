package org.example.service.Interfaces;

import org.example.servlet.dto.GroupIncomingDto;
import org.example.servlet.dto.GroupOutGoingDto;
import org.example.servlet.dto.GroupUpdateDto;

public interface GroupService {
    GroupOutGoingDto save(GroupIncomingDto role);

    void update(GroupUpdateDto role) throws RuntimeException;

    GroupOutGoingDto findById(Long roleId) throws RuntimeException;

    boolean delete(Long roleId) throws RuntimeException;
}
