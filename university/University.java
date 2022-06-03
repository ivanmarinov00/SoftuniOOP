import java.util.ArrayList;
import java.util.List;

public class University {
    List<Student> students = new ArrayList<>();
    private int capacity;

    public University(int capacity) {
        this.capacity = capacity;
    }

    public List<Student> getStudents() {
        return students;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getStudentCount(){
        return this.students.size();
    }
    public String registerStudent(Student student) {
        StringBuilder sb = new StringBuilder();
        if (this.students.size() < this.capacity) {
            if (!this.students.contains(student)) {
                this.students.add(student);
                sb.append("Added student ")
                        .append(student.getFirstName())
                        .append(" ")
                        .append(student.getLastName());
            } else if (this.students.contains(student)) {
                sb
                        .append("Student is already in the university");
            }
        } else {
            sb
                    .append("No seats in the university");
        }
        return sb.toString();
    }
    public String dismissStudent(Student student){
        StringBuilder output = new StringBuilder();
        if (this.students.contains(student)) {
            output
                    .append("Removed student ")
                    .append(student.getFirstName())
                    .append(" ")
                    .append(student.getLastName());
            this.students.remove(student);
        } else {
            output
                    .append("Student not found");
        }
        return output.toString();
    }
    public Student getStudent(String firstName, String lastName) {
        return this.students
                .stream()
                .filter(student -> student.getFirstName().equals(firstName) && student.getLastName().equals(lastName))
                .findFirst()
                .orElse(null);
    }
    public String getStatistics() {
        StringBuilder output = new StringBuilder();
        this.students
                .forEach(student -> output
                        .append("==Student: First Name=")
                        .append(student.getFirstName())
                        .append(", Last Name=")
                        .append(student.getLastName())
                        .append(", Best Subject=")
                        .append(student.getBestSubject())
                        .append(System.lineSeparator()));
        return output.toString().trim();
    }
}
