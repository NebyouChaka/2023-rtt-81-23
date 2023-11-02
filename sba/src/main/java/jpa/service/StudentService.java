package jpa.service;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Student;
import jpa.entitymodels.Course;

import java.util.List;

public class StudentService {
    private final StudentDAO studentDAO;

    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    public Student getStudentByEmail(String sEmail) {
        return studentDAO.getStudentByEmail(sEmail);
    }

    public boolean validateStudent(String sEmail, String sPassword) {
        return studentDAO.validateStudent(sEmail, sPassword);
    }

    public void registerStudentToCourse(String sEmail, int cId) {
        studentDAO.registerStudentToCourse(sEmail, cId);
    }

    public List<Course> getStudentCourses(String sEmail) {
        return studentDAO.getStudentCourses(sEmail);
    }
}
