package org.example.repository.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.example.repository.Interfaces.GroupRepository;
import org.junit.Test;

public class GroupRepositoryImplTest {

    @Test
    public void testGetInstance() {
        GroupRepository repository1 = GroupRepositoryImpl.getInstance();
        GroupRepository repository2 = GroupRepositoryImpl.getInstance();

        assertEquals(repository1,repository2);
    }
}
