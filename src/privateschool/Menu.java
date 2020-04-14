package privateschool;

import static privateschool.Check.getIntFromTo;
import static privateschool.MainClass.assignments;
import static privateschool.MainClass.courses;
import static privateschool.MainClass.formatter;
import static privateschool.MainClass.inspectMenuOptions;
import static privateschool.MainClass.students;
import static privateschool.MainClass.trainers;
import static privateschool.Print.printAssignmentsPerCourse;
import static privateschool.Print.printAssignmentsPerStudent;
import static privateschool.Print.printList;
import static privateschool.Print.printStudentsMoreThanOneCourse;
import static privateschool.Print.printStudentsPerCourse;
import static privateschool.Print.printTrainersPerCourse;
import static privateschool.Register.regAssignment;
import static privateschool.Register.regCourse;
import static privateschool.Register.regStudent;
import static privateschool.Register.regTrainer;


public class Menu {
    public static void mainMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1.Insert some data");
        System.out.println("2.Inspect data");
        System.out.println("3.Exit application");
        int mainMenuChoice = getIntFromTo(1, 3);
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
}
