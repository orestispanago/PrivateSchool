package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import privateschool.MainClass;

public class Assignment {

    private String title;
    private String description;
    private LocalDate subDateTime;
    private int oralMark;
    private int totalMark;
    private List<Course> courses = new ArrayList();

    public Assignment(String title, String description) {
        this.title = title;
        this.description = description;
//        this.subDateTime = subDateTime;
        MainClass.assignments.add(this);
    }
public Assignment(String title, String description,LocalDate subDateTime){
    this(title, description);
    this.subDateTime = subDateTime;
}
    public void addCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
        } else {
            System.out.println("Course has already been added to assignment!");
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSubDateTime(LocalDate subDateTime) {
        this.subDateTime = subDateTime;
    }

    public void setOralMark(int oralMark) {
        this.oralMark = oralMark;
    }

    public void setTotalMark(int totalMark) {
        this.totalMark = totalMark;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public int getCoursesNumber() {
        return courses.size();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getSubDateTime() {
        return subDateTime;
    }

    public String getSubDateTimeStringGreek() {
        return subDateTime.format(MainClass.formatterGreek);
    }
    public String getSubDateTimeStringSQL() {
        return subDateTime.format(MainClass.formatterSQL);
    }

    public int getOralMark() {
        return oralMark;
    }

    public int getTotalMark() {
        return totalMark;
    }

    @Override
    public String toString() {
        return String.format("Assignment: %-7s %-12s %s\n", title,
                description, getSubDateTimeStringGreek());
    }
}
