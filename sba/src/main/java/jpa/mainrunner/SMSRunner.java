package jpa.mainrunner;

import java.util.List;
import java.util.Scanner;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.entitymodels.StudentCourse;
import jpa.service.CourseService;
import jpa.service.StudentCourseService;
import jpa.service.StudentService;

public class SMSRunner {

    public static void main(String[] args) {
        System.out.println("Are you a");
        System.out.println("1. Student");
        System.out.println("2. Quit");
        System.out.print("Answer: ");
        Scanner in = new Scanner(System.in);

        int answer = in.nextInt();

        if (answer == 1) {
            StudentService studentService = new StudentService();
            CourseService courseService = new CourseService();
            StudentCourseService studentCourseService = new StudentCourseService();

            System.out.println("Enter your email: ");
            in.nextLine();
            String email = in.nextLine();
            System.out.println("Enter your password: ");
            String password = in.nextLine();

            if (studentService.validateStudent(email, password)) {
                Student student = studentService.getStudentByEmail(email);
                List<Course> courseList = courseService.getAllCourses();
                List<StudentCourse> studentCourses = studentCourseService.getAllStudentCourses(null, student.getEmail());

                myClasses(studentCourses, courseList);

                System.out.println("What would you like to do?");
                System.out.println("1. Register for a new Class");
                System.out.println("2. Log Out");
                System.out.print("Answer: \n");
                answer = in.nextInt();

                if (answer == 1) {
                    // Display a list of all Classes
                    allClasses(courseList);

                    System.out.print("Select Course by ID Number: ");
                    int courseID = in.nextInt();
                    System.out.println("\n Attempting to Register...");
                    studentCourseService.registerStudentToCourse(null, student.getEmail(), courseID);
                }

                System.out.println("Logging Out...");
            } else {
                System.out.println("Invalid Email or Password.");
            }
        }

        System.out.println("Closing Program. Goodbye.");
    }

    public static void myClasses(List<StudentCourse> studentCourses, List<Course> courseList) {
        System.out.println("My Classes: ");
        System.out.printf("%-5s|%-25s|%-25s", "#", "COURSE NAME", "INSTRUCTOR NAME \n");

        for (StudentCourse studentCourse : studentCourses) {
            int courseID = studentCourse.getCourseID();
            Course course = CourseService.getCourseById(courseID);

            if (course != null) {
                System.out.printf("%-5s|%-25s|%-25s\n", course.getId(), course.getName(), course.getInstructorName());
            } else {
                System.out.println("Course not found for StudentCourse.");
            }
        }
    }

    public static void allClasses(List<Course> courseList) {
        System.out.println("All Classes: ");
        System.out.printf("%-5s|%-25s|%-25s", "#", "COURSE NAME", "INSTRUCTOR NAME \n");
        for (Course course : courseList) {
            System.out.printf("%-5s|%-25s|%-25s\n", course.getId(), course.getName(), course.getInstructorName());
        }
    }
}
