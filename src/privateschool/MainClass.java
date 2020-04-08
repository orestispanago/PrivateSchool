package privateschool;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MainClass {

    public static List<Course> courses = new ArrayList();
    public static List<Student> students = new ArrayList();
    public static List<Trainer> trainers = new ArrayList();
    public static List<Assignment> assignments = new ArrayList();
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static Scanner scanner = new Scanner(System.in);
    private static List<String> inspectMenuOptions = new ArrayList(Arrays.asList(
            "Students\n",//
            "Trainers\n",//
            "Assignments\n",//
            "Courses\n",//
            "Students per course\n",//
            "Trainers per course\n",//
            "Assignments per course\n",//
            "Assignments per student\n",//
            "Students that belong to more than one courses\n",
            "Return to main menu\n"));//

//==============================Dummy data======================================
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

//==============================Checks==========================================
    static boolean isInputYes() {
        char input = scanner.next(".").charAt(0);
        while (input != 'y' && input != 'n') {
            System.out.print("Please type 'y' for yes or 'n' for no:");
            input = scanner.next(".").charAt(0);
        }
        return input == 'y';
    }

    static boolean isJava() {
        System.out.print("Please type 'j' for Java or 'c' for C#:");
        char input = scanner.next(".").charAt(0);
        while (input != 'j' && input != 'c') {
            System.out.print("Please type 'j' for java or 'c' for C#:");
            input = scanner.next(".").charAt(0);
        }
        return input == 'j';
    }

    static boolean isFullTime() {
        System.out.print("Please type 'f' for full-time or 'p' for part-time:");
        char input = scanner.next(".").charAt(0);
        while (input != 'f' && input != 'p') {
            System.out.print("Please type 'f' for full-time or 'p' for part-time:");
            input = scanner.next(".").charAt(0);
        }
        return input == 'f';
    }

    static String getCourseStream() {
        if (!isJava()) {
            return "C#";
        }
        return "Java";
    }

    static String getCourseType() {
        if (!isFullTime()) {
            return "part-time";
        }
        return "full-time";
    }

    static int getIntFromTo(int from, int to) {
        int num;
        do {
            System.out.printf("Please select from %d to %d:", from, to);
            while (!scanner.hasNextInt()) {
                System.out.printf("Please select from %d to %d:", from, to);
                scanner.next();
            }
            num = scanner.nextInt();
        } while (num < from || num > to);
        return num;
    }

    static boolean isDateValid(String dateString, DateTimeFormatter formatter) {
        try {
            LocalDate.parse(dateString, formatter);
        } catch (java.time.format.DateTimeParseException e) {
            return false;
        }
        return true;
    }

    static LocalDate getDate(String message, DateTimeFormatter formatter) {
        String dateString = "";
        boolean isValid = false;
        while (!isValid) {
            System.out.printf("Enter %s date (dd/mm/yyyy):", message);
            dateString = scanner.next();
            isValid = isDateValid(dateString, formatter);
        }
        return LocalDate.parse(dateString, formatter);
    }

    static LocalDate getDateOneMonthFrom(LocalDate date1, String message, DateTimeFormatter formatter) {
        LocalDate date2 = getDate(message, formatter);
        while (!date2.isAfter(date1.plusMonths(1))) {
            if (date2.isBefore(date1)) {
                System.out.println("Please enter a date after course start!");
            } else {
                System.out.println("A course can be no shorter than a month!");
            }
            date2 = getDate(message, formatter);
        }
        return date2;
    }

    static LocalDate getSubDateTime(Course course) {
        LocalDate subDateTime = getDate("submission", formatter);
        LocalDate courseStart = course.getStart_date();
        LocalDate courseEnd = course.getEnd_date();
        String courseStartString = course.getStart_dateString();
        String courseEndString = course.getEnd_dateString();
        while (subDateTime.isBefore(courseStart) || subDateTime.isAfter(courseEnd)) {
            System.out.printf("Please enter a date between %s and %s.\n", courseStartString, courseEndString);
            subDateTime = getDate("submission", formatter);
            while (!isWorkDay(subDateTime)) {
                System.out.println("Assignments cannot be submitted at weekend, please enter a work day.");
                subDateTime = getDate("submission", formatter);
            }
        }
        return subDateTime;
    }

    static boolean isWorkDay(LocalDate date) {
        int dayOfWeek = date.getDayOfWeek().getValue();
        return !(dayOfWeek == 6 || dayOfWeek == 7);
    }
//==============================Registers=======================================

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

//==============================Adders==========================================
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

//==============================Printers========================================
    public static void printList(List listName) {
        for (int i = 0; i < listName.size(); i++) {
            System.out.print(i + 1 + "." + listName.get(i).toString());
        }
    }

    static void printTrainersPerCourse() {
        System.out.println("Printing trainers per course...");
        for (Course co : courses) {
            System.out.print(co);
            printList(co.getTrainers());
        }
    }

    static void printStudentsPerCourse() {
        System.out.println("Printing students per course...");
        for (Course co : courses) {
            System.out.print(co);
            printList(co.getStudents());
        }
    }

    static void printAssignmentsPerCourse() {

        System.out.println("Printing assignments per course...");
        for (Course co : courses) {
            System.out.print(co);
            printList(co.getAssignments());
        }
    }

    static void printAssignmentsPerStudent() {
        System.out.println("Printing assignments per student...");
        for (Student st : students) {
            System.out.print(st);
            printList(st.getAssignments());
        }
    }

    static void printStudentsMoreThanOneCourse() {
        if (courses.size() < 2) {
            System.out.println("Only one course found, please enter more from the main menu.");
        } else {
            System.out.println("Printing students enrolled to more than one course...");
            for (Student st : students) {
                if (st.getCoursesNumber() > 1) {
                    System.out.print(st);
                    printList(st.getCourses());
                }
            }
        }
    }

//==============================Submitts this week==============================
    public static List getWorkDays(LocalDate date) {
        List<LocalDate> workdays = new ArrayList();
        int dayOfWeek = date.getDayOfWeek().getValue();
        LocalDate mondayOfWeek = date.minusDays(dayOfWeek - 1);
        for (int i = 0; i < 5; i++) {
            workdays.add(mondayOfWeek.plusDays(i));
        }
        return workdays;
    }

    public static List<String> getSubmissionsThisWeek(List<LocalDate> workDays) {
        List<String> submissionsThisWeek = new ArrayList();
        String submissions = "";
        for (LocalDate day : workDays) {
            for (Assignment a : assignments) {
                if (a.getSubDateTime().equals(day)) {
                    for (Course c : a.getCourses()) {
                        for (Student s : c.getStudents()) {
                            submissions = String.format("%-10s %-15s %-15s %-13s %-1s\n",
                                    s.getFirstName(),
                                    s.getLastName(),
                                    a.getTitle(),
                                    a.getSubDateTimeString(),
                                    c.getTitle());
                            submissionsThisWeek.add(submissions);
                        }
                    }
                }
            }
        }
        return submissionsThisWeek;
    }

    public static void whoSubmittsThisWeek(DateTimeFormatter formatter) {
        do {
            LocalDate date = getDate("", formatter);
            List<LocalDate> workDays = getWorkDays(date);
            List<String> submissions = getSubmissionsThisWeek(workDays);
            if (submissions.size() == 0) {
                System.out.println("No assignments are submitted this week!");
            } else {
                printList(submissions);
            }
            System.out.print("Do you want to check another date? (y/n):");
        } while (isInputYes());

    }

//==============================Menus===========================================
    public static void mainMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1.Insert some data");
        System.out.println("2.Inspect data");
        System.out.println("3.See weekly assignments for a day");
        System.out.println("4.Exit application");
        int mainMenuChoice = getIntFromTo(1, 4);
        switch (mainMenuChoice) {
            case 1:
                regCourse(formatter);
                regTrainer(formatter);
                regAssignment(formatter);
                regStudent(formatter);
                break;
            case 2:
                inspectDataMenu();
                break;
            case 3:
                whoSubmittsThisWeek(formatter);
                mainMenu();
            case 4:
                System.out.println("Exiting...");
                System.exit(0);
        }
    }

    static void inspectDataMenu() {
        if (!students.isEmpty()) {
            System.out.println("You can view the following lists:");
            printList(inspectMenuOptions);
            int choice = getIntFromTo(1, inspectMenuOptions.size());
            switch (choice) {
                case 1:
                    printList(students);
                    inspectDataMenu();
                    break;
                case 2:
                    printList(trainers);
                    inspectDataMenu();
                    break;
                case 3:
                    printList(assignments);
                    inspectDataMenu();
                    break;
                case 4:
                    printList(courses);
                    inspectDataMenu();
                    break;
                case 5:
                    printStudentsPerCourse();
                    inspectDataMenu();
                    break;
                case 6:
                    printTrainersPerCourse();
                    inspectDataMenu();
                    break;
                case 7:
                    printAssignmentsPerCourse();
                    inspectDataMenu();
                    break;
                case 8:
                    printAssignmentsPerStudent();
                    inspectDataMenu();
                case 9:
                    printStudentsMoreThanOneCourse();
                    inspectDataMenu();
                case 10:
                    break;
            }
        } else {
            System.out.println("No data found, please enter some from the main menu.");
        }
        mainMenu();
    }

    public static void main(String[] args) {
        dummyData();
        mainMenu();
    }
}
