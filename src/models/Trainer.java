package models;

import models.Course;
import java.util.ArrayList;
import java.util.List;
import privateschool.MainClass;

public class Trainer {

    private String firstName;
    private String lastName;
    private String subject;
    private List<Course> courses = new ArrayList();

    public Trainer(String firstName, String lastName, String subject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
        MainClass.trainers.add(this);
    }

    public void addCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
        } else {
            System.out.println("Course has already been added to trainer!");
        }

    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public int getCoursesNumber() {
        return courses.size();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return String.format("Trainer: %-10s %-12s %s\n", firstName, lastName, subject);
    }
}
