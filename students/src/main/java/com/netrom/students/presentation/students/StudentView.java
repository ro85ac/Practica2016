/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netrom.students.presentation.students;

import com.netrom.students.business.boundary.Students;
import com.netrom.students.business.entity.Student;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author EGLOBAL
 */
@Named
@ViewScoped
public class StudentView implements Serializable {

    @Inject
    private Students studentBoundary;
    private List<Student> students;

    private Student selectedStudent;

    @PostConstruct
    public void init() {
        students = studentBoundary.getAll();
        selectedStudent = new Student();
    }

    public void saveStudent() {
        if (selectedStudent.getId() == null) {
            students.add(selectedStudent);
        }
        studentBoundary.save(selectedStudent);
        this.selectedStudent = new Student();
    }

    public void deleteStudent() {
        studentBoundary.delete(selectedStudent);
        students.remove(selectedStudent);
        this.selectedStudent = new Student();
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
    }
}
