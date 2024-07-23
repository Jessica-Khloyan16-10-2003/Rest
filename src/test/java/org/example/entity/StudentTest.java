package org.example.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    @Test
    void testConstructor() {
        Long id = 1L;
        String firstName = "John";
        String lastName = "Doe";
        Group group = new Group();
        List<OptionalClass> optionalClassList = new ArrayList<>();
        Student student = new Student(id, firstName, lastName, group, optionalClassList);
        assertEquals(id, student.getId());
        assertEquals(firstName, student.getFirstName());
        assertEquals(lastName, student.getSurname());
        assertEquals(group, student.getGroup());
        assertEquals(optionalClassList, student.getOptionalClassList());
    }

    @Test
    void testGettersAndSetters() {
        Student student = new Student();
        student.setFirstName("Jane");
        student.setSurname("Smith");
        student.setGroup(new Group());
        student.setOptionalClassList(new ArrayList<>());

        // Assert
        assertEquals(null, student.getId());
        assertEquals("Jane", student.getFirstName());
        assertEquals("Smith", student.getSurname());
        assertFalse(new Group() == student.getGroup());
        assertTrue(student.getOptionalClassList().isEmpty());
    }
}