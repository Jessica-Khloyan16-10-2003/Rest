package org.example.entity;

public class StudentToOptionalClass {
    private Long id;
    private Long studentId;
    private Long optionalClassId;

    public StudentToOptionalClass() {
    }

    public StudentToOptionalClass(Long id, Long studentIdId, Long optionalClassId) {
        this.id = id;
        this.studentId = studentIdId;
        this.optionalClassId = optionalClassId;
    }

    public Long getId() {
        return id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getOptionalClassId() {
        return optionalClassId;
    }

    public void setOptionalClassId(Long optionalClassId) {
        this.optionalClassId = optionalClassId;
    }
}
