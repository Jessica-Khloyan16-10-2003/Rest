package org.example.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions. * ;

class StudentToOptionalClassTest {

    @Test
    void testConstructor() {
        StudentToOptionalClass studentToOptionalClass = new StudentToOptionalClass();
        assertNull(studentToOptionalClass.getId());
        assertNull(studentToOptionalClass.getStudentId());
        assertNull(studentToOptionalClass.getOptionalClassId());

        Long id = 1L;
        Long studentId = 2L;
        Long optionalClassId = 3L;
        StudentToOptionalClass expectedStudentToOptionalClass = new StudentToOptionalClass(id, studentId, optionalClassId);
        assertEquals(expectedStudentToOptionalClass.getId(), id);
        assertEquals(expectedStudentToOptionalClass.getStudentId(), studentId);
        assertEquals(expectedStudentToOptionalClass.getOptionalClassId(), optionalClassId);
    }

    @Test
    void testGettersAndSetters() {
        Long id = 1L;
        Long studentId = 2L;
        Long optionalClassId = 3L;
        StudentToOptionalClass studentToOptionalClass = new StudentToOptionalClass(null, null, null);
        studentToOptionalClass.setStudentId(studentId);
        studentToOptionalClass.setOptionalClassId(optionalClassId);
        assertEquals(studentToOptionalClass.getId(), null);
        assertEquals(studentToOptionalClass.getStudentId(), studentId);
        assertEquals(studentToOptionalClass.getOptionalClassId(), optionalClassId);

    }
}
