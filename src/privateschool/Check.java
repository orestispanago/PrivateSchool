package privateschool;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import models.Course;
import static privateschool.MainClass.formatter;
import static privateschool.MainClass.scanner;


public class Check {
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
}
