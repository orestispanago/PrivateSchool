package privateschool;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Course {

    private String title;
    private String stream;
    private String type;
    private LocalDate start_date;
    private LocalDate end_date;
    private List<Student> students = new ArrayList();
    private List<Assignment> assignments = new ArrayList();
    private List<Trainer> trainers = new ArrayList();

    public Course(String title, String stream, String type, LocalDate start_date, LocalDate end_date) {
        this.title = title;
        this.stream = stream;
        this.type = type;
        this.start_date = start_date;
        this.end_date = end_date;
        MainClass.courses.add(this);
    }

    public void addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
        } else {
            System.out.println("Student has already been registered to course!");
        }
    }

    public void addAssignment(Assignment assignment) {
        if (!assignments.contains(assignment)) {
            assignments.add(assignment);
            for (Student st : students) {
                st.addAssignment(assignment);
            }
        } else {
            System.out.println("Assignment has already been added to course!");
        }
    }

    public void addTrainer(Trainer trainer) {
        trainers.add(trainer);
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public String getTitle() {
        return title;
    }

    public String getStream() {
        return stream;
    }

    public String getType() {
        return type;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public String getStart_dateString() {
        return start_date.format(MainClass.formatter);
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public String getEnd_dateString() {
        return end_date.format(MainClass.formatter);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    @Override
    public String toString() {
        return String.format("Course: %-7s %-12s %s %s to %s\n", title,
                stream, type, getStart_dateString(), getEnd_dateString());
    }
}
