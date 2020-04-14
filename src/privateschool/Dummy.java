package privateschool;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import models.Assignment;
import models.Course;
import models.Student;
import models.Trainer;
import static privateschool.Check.isInputYes;
import static privateschool.MainClass.formatter;


public class Dummy {
    public static LocalDate randomBirthDate() {
        Random random = new Random();
        int minDay = (int) LocalDate.of(1950, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(1970, 1, 1).toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);
        LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);
        return randomBirthDate;
    }

    public static LocalDate stringToLocalDate(String dateString, DateTimeFormatter formatter) {
        return LocalDate.parse(dateString, formatter);
    }

    public static void loadDataset() {
        Student s1 = new Student("Bruce", "Dickinson", randomBirthDate());
        Student s2 = new Student("Ronnie", "Dio", randomBirthDate());
        Student s3 = new Student("Eric", "Clapton", randomBirthDate());
        Student s4 = new Student("Alice", "Cooper", randomBirthDate());
        Student s5 = new Student("Axl", "Rose", randomBirthDate());
        Student s6 = new Student("Ozzy", "Osbourne", randomBirthDate());
        Student s7 = new Student("Robert", "Plant", randomBirthDate());
        Student s8 = new Student("Rob", "Halford", randomBirthDate());
        Student s9 = new Student("Lemmy", "Killmister", randomBirthDate());
        Student s10 = new Student("Bob", "Dylan", randomBirthDate());

        Trainer t1 = new Trainer("Jimmy", "Hendrix", "Java");
        Trainer t2 = new Trainer("Jim", "Morrison", "Python");
        Trainer t3 = new Trainer("Janis", "Joplin", "SQL");

        LocalDate courseStart = stringToLocalDate("01/03/2020", formatter);
        LocalDate courseEnd = stringToLocalDate("01/05/2020", formatter);

        Course c1 = new Course("Web development1", "Front End", "Full time", courseStart, courseEnd);
        Course c2 = new Course("Web development2", "Back End", "Full time", courseStart, courseEnd);
        Course c3 = new Course("Mob development", "Front End", "Full time", courseStart, courseEnd);

        LocalDate submissionDate1 = stringToLocalDate("18/03/2020", formatter);
        LocalDate submissionDate2 = stringToLocalDate("16/03/2020", formatter);

        Assignment a1 = new Assignment("Assignment 1", "An easy one", submissionDate1);
        Assignment a2 = new Assignment("Assignment 2", "A hard one", submissionDate2);

        t1.addCourse(c1);
        t1.addCourse(c2);
        t2.addCourse(c1);
        t2.addCourse(c2);
        t3.addCourse(c1);

        c1.addStudent(s1);
        c1.addStudent(s2);
        c1.addStudent(s3);
        c1.addStudent(s4);
        c2.addStudent(s5);
        c2.addStudent(s6);
        c2.addStudent(s7);
        c2.addStudent(s8);
        c2.addStudent(s9);
        c2.addStudent(s10);
        s1.addCourse(c1);
        s1.addCourse(c2);
        s1.addCourse(c3);
        s2.addCourse(c1);
        s3.addCourse(c1);
        s3.addCourse(c2);
        s4.addCourse(c1);
        s5.addCourse(c2);
        s6.addCourse(c2);
        s7.addCourse(c2);
        s8.addCourse(c2);
        s9.addCourse(c2);
        s10.addCourse(c2);

        a1.addCourse(c1);
        a1.addCourse(c2);
        a2.addCourse(c1);
        c1.addAssignment(a1);
        c2.addAssignment(a1);
        c1.addAssignment(a2);
    }

    static void dummyData() {
        System.out.print("Would you like to use dummy data? (y/n):");
        boolean useDummyData = isInputYes();
        if (useDummyData == true) {
            loadDataset();
        }
    }
}
