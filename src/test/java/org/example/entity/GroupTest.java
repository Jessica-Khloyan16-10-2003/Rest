package org.example.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {

    @Test
    public void testGetId() {
        Long id = 1L;
        String name = "Test Group";
        Group group = new Group(id, name);
        assertEquals(id, group.getId());
    }

    @Test
    public void testGetName() {
        Long id = 1L;
        String name = "Test Group";
        Group group = new Group(id, name);
        assertEquals(name, group.getName());
    }

    @Test
    public void testSetName() {
        Long id = 1L;
        String name = "Test Group";
        Group group = new Group(id, name);
        String newName = "New Group Name";
        group.setName(newName);
        assertEquals(newName, group.getName());
    }

}