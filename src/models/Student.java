package models;

import models.Course;
import models.Assignment;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import privateschool.MainClass;

public class Student {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private double tuitionFees;
    private List<Course> courses = new ArrayList();
    private List<Assignment> assignments = new ArrayList();

    public Student(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        MainClass.students.add(this);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addAssignment(Assignment assignment) {
        if (!assignments.contains(assignment)){
            assignments.add(assignment);
        }
        else{
            System.out.printf("Assignment already exists for student %s %s\n",firstName,lastName);
        }
        
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setTuitionFees(double tuitionFees) {
        this.tuitionFees = tuitionFees;
    }

    public List<Course> getCourses() {
        return courses;
    }
    public int getCoursesNumber() {
        return courses.size();
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }
    public int getAssignmentsNumber(){
        return assignments.size();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDateOfBirthStringGreek() {
        return dateOfBirth.format(MainClass.formatterGreek);
    }
    public String getDateOfBirthStringSQL() {
        return dateOfBirth.format(MainClass.formatterSQL);
    }
    public double getTuitionFees() {
        return tuitionFees;
    }

    @Override
    public String toString() {
        return String.format("Student: %-10s %-12s %s\n", firstName, lastName, getDateOfBirthStringGreek());
    }
}
