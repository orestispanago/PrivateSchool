package privateschool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import models.AssignmentData;
import models.AssignmentsCourses;
import models.CourseData;
import models.StudentData;
import models.StudentsCourses;
import models.TrainerData;
import models.TrainersCourses;
import static privateschool.Check.getIntFromTo;
import static privateschool.MainClass.formatterGreek;
import static privateschool.Print.printList;

public class Menu {

    public static List<String> inspectMenuOptions = new ArrayList(Arrays.asList(
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

    public static void mainMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1.Insert some data");
        System.out.println("2.Inspect data");
        System.out.println("3.Exit application");
        int mainMenuChoice = getIntFromTo(1, 3);
        switch (mainMenuChoice) {
            case 1:
                Register.course(formatterGreek);
                Register.trainer(formatterGreek);
                Register.assignment(formatterGreek);
                Register.student(formatterGreek);
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
        System.out.println("You can view the following lists:");
        printList(inspectMenuOptions);
        int choice = getIntFromTo(1, inspectMenuOptions.size());
        switch (choice) {
            case 1:
                StudentData.printAll();
                inspectDataMenu();
                break;
            case 2:
                TrainerData.printAll();
                inspectDataMenu();
                break;
            case 3:
                AssignmentData.printAll();
                inspectDataMenu();
                break;
            case 4:
                CourseData.printAll();
                inspectDataMenu();
                break;
            case 5:
                CourseData.printAll();
                StudentsCourses.printStudentsPerCourse();
                inspectDataMenu();
                break;
            case 6:
                CourseData.printAll();
                TrainersCourses.printTrainersPerCourse();
                inspectDataMenu();
                break;
            case 7:
                CourseData.printAll();
                AssignmentsCourses.printAssignmentsPerCourse();
                inspectDataMenu();
                break;
            case 8:
                StudentData.printAll();
                AssignmentsCourses.printAssignmentsPerCoursePerStudent();
                inspectDataMenu();
            case 9:
                StudentsCourses.printStudentsMoreThanOneCourse();
                inspectDataMenu();
            case 10:
                break;
        }
        mainMenu();
    }
}
