package privateschool;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import models.Assignment;
import models.Course;
import models.Student;
import models.Trainer;
import static privateschool.Check.getCourseStream;
import static privateschool.Check.getCourseType;
import static privateschool.Check.getDate;
import static privateschool.Check.getDateOneMonthFrom;
import static privateschool.Check.getIntFromTo;
import static privateschool.Check.getSubDateTime;
import static privateschool.Check.isInputYes;
import static privateschool.MainClass.courses;
import static privateschool.MainClass.scanner;
import static privateschool.Menu.mainMenu;
import static privateschool.Print.printList;


public class Register {
    static void regCourse(DateTimeFormatter formatter) {
        do {
            System.out.print("Enter a course title:");
            String title = scanner.next();
            System.out.println("Select course's stream,");
            String stream = getCourseStream();
            String type = getCourseType();
            LocalDate start_date = getDate("course start", formatter);
            LocalDate end_date = getDateOneMonthFrom(start_date, "course end", formatter);
            Course c1 = new Course(title, stream, type, start_date, end_date);
            System.out.print("Do you want to register another course? (y/n):");
        } while (isInputYes());
    }

    static void regTrainer(DateTimeFormatter formatter) {
        do {
            System.out.print("Enter the trainer's first name:");
            String firstName = scanner.next();
            System.out.print("Enter the trainer's last name:");
            String lastName = scanner.next();
            System.out.print("Enter the trainer's subject:");
            String subject = scanner.next();
            Trainer t1 = new Trainer(firstName, lastName, subject);
            addCoursesToTrainer(t1);
            System.out.print("Do you want to register another trainer? (y/n):");
        } while (isInputYes());
    }

    static void regAssignment(DateTimeFormatter formatter) {
        do {
            System.out.print("Enter assignment title:");
            String title = scanner.next();
            System.out.print("Enter assignment description:");
            String description = scanner.next();
            Assignment a1 = new Assignment(title, description);
            Course course = addCoursesToAssignment(a1);
            LocalDate subDatetime = getSubDateTime(course);
            a1.setSubDateTime(subDatetime);
            System.out.print("Do you want to register another assignment? (y/n):");
        } while (isInputYes());
    }

    static void regStudent(DateTimeFormatter formatter) {
        do {
            System.out.print("Enter the student's first name:");
            String firstName = scanner.next();
            System.out.print("Enter the student's last name:");
            String lastName = scanner.next();
            LocalDate dateOfBirth = getDate("birth date", formatter);
            Student s1 = new Student(firstName, lastName, dateOfBirth);
            addCoursesToStudent(s1);
            System.out.print("Do you want to register another student? (y/n):");
        } while (isInputYes());
        mainMenu();
    }
        static Course addCoursesToAssignment(Assignment assignment) {
        Course course;
        do {
            System.out.println("Which course is the assignment for?");
            printList(courses);
            int courseID = getIntFromTo(1, courses.size()) - 1;
            course = courses.get(courseID);
            course.addAssignment(assignment);
            assignment.addCourse(course);
            System.out.print("Do you want to add the assignment to another course? (y/n):");
        } while (isInputYes() && assignment.getCoursesNumber() < courses.size());
        if (assignment.getCoursesNumber() == courses.size()) {
            System.out.println("Assignment has been added to all available courses!");
        }
        return course;
    }

    static void addCoursesToTrainer(Trainer trainer) {
        do {
            System.out.println("Which course is the trainer going to teach?");
            printList(courses);
            int courseID = getIntFromTo(1, courses.size()) - 1;
            Course course = courses.get(courseID);
            trainer.addCourse(course);
            course.addTrainer(trainer);
            System.out.print("Do you want to add another course to this trainer? (y/n):");
        } while (isInputYes() && trainer.getCoursesNumber() < courses.size());
        if (trainer.getCoursesNumber() == courses.size()) {
            System.out.println("All available courses are added to this trainer!");
        }
    }

    static void addCoursesToStudent(Student student) {
        do {
            System.out.println("Which course is the student going to attend?");
            printList(courses);
            int courseID = getIntFromTo(1, courses.size()) - 1;
            Course course = courses.get(courseID);
            course.addStudent(student);
            student.addCourse(course);
            System.out.print("Register this student to another course? (y/n):");
        } while (isInputYes() && student.getCoursesNumber() < courses.size());
        if (student.getCoursesNumber() == courses.size()) {
            System.out.println("Student has enrolled to all available courses!");
        }
    }

}
