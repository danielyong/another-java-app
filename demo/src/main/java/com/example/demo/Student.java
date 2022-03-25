package com.example.demo;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name="Student")
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private long ID;
    @Column(name="student_name")
    private String studentName;

    @OneToMany
    @MapKey(name = "id")
    @OrderBy("course_name")
    private List<Courses> enrolledCourses;

    public void setID(long iD) {
        ID = iD;
    }
    public long getID() {
        return ID;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setEnrolledCourses(List<Courses> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }
    public List<Courses> getEnrolledCourses() {
        return enrolledCourses;
    }
}
