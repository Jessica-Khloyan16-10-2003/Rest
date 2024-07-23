package org.example.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class OptionalClassTest {
    @Test
    public void testGetters() {
        OptionalClass optionalClass = new OptionalClass(1L, "Math", new ArrayList<>());

        Assertions.assertEquals(1L, optionalClass.getId());
        Assertions.assertEquals("Math", optionalClass.getName());
        Assertions.assertEquals(new ArrayList<>(), optionalClass.getStudentListList());
    }

    @Test
    public void testSetters() {
        OptionalClass optionalClass = new OptionalClass();

        optionalClass.setName("Physics");
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1L,"Jessica","Khloyan",null,null));
        optionalClass.setStudentListList(studentList);

        Assertions.assertEquals(null , optionalClass.getId());
        Assertions.assertEquals("Physics", optionalClass.getName());
        Assertions.assertEquals(studentList, optionalClass.getStudentListList());
    }

}