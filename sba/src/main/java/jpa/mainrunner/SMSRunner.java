package jpa.mainrunner;

import jpa.entitymodels.Student;
import jpa.dao.CourseDAO;
import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.service.StudentService;
import jpa.service.CourseService;

import java.util.List;
import java.util.Scanner;

public class SMSRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
         
        StudentDAO studentDAO = new YourStudentDAOImplementation();
        CourseDAO courseDAO = new YourCourseDAOImplementation();
		StudentService studentService = new StudentService(studentDAO);
		CourseService courseService = new CourseService(courseDAO);

        while (true) {
            System.out.println("Are you a(n)");
            System.out.println("1. Student");
            System.out.println("2. quit");

            System.out.print("Please, enter 1 or 2: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            if (choice == 1) {
                System.out.print("Enter Your Email: ");
                String email = scanner.nextLine();
                System.out.print("Enter Your Password: ");
                String password = scanner.nextLine();

                if (studentService.validateStudent(email, password)) {
                    List<Course> studentCourses = studentService.getStudentCourses(email);
                    System.out.println("My Classes:");
                    System.out.println("#   COURSE NAME  INSTRUCTOR NAME");
                    for (Course course : studentCourses) {
                        System.out.printf("%-4d%-15s%-15s%n", course.getId(), course.getName(), course.getInstructorName());
                    }

                    System.out.println("1. Register to Class");
                    System.out.println("2. Logout");

                    System.out.print("Select an option: ");
                    int additionalChoice = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline

                    if (additionalChoice == 1) {
                        System.out.println("All Courses:");
                        List<Course> allCourses = courseService.getAllCourses();
                        System.out.println("ID   COURSE NAME  INSTRUCTOR NAME");
                        for (Course course : allCourses) {
                            System.out.printf("%-4d%-15s%-15s%n", course.getId(), course.getName(), course.getInstructorName());
                        }

                        System.out.print("Which Course? Enter the course ID: ");
                        int courseId = scanner.nextInt();
                        scanner.nextLine();  // Consume the newline

                        // Check if the student is already registered for the selected course
                        if (studentCourses.stream().anyMatch(c -> c.getId() == courseId)) {
                            System.out.println("You are already registered in that course!");
                        } else {
                            // Register the student for the selected course
                            studentService.registerStudentToCourse(email, courseId);
                            studentCourses = studentService.getStudentCourses(email);  // Update the list of registered courses
                            System.out.println("You have been registered for the course.");
                        }
                    } else if (additionalChoice == 2) {
                        System.out.println("You have been signed out.");
                        break;
                    }
                } else {
                    System.out.println("Invalid credentials. Program ends.");
                    break;
                }
            } else if (choice == 2) {
                System.out.println("Program ends. Goodbye!");
                break;
            } else {
                System.out.println("Invalid option. Please select 1 (Student) or 2 (quit).");
            }
        }
    }
}