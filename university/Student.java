public class Student {
    private String firstName;
    private String lastName;
    private String bestSubject;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBestSubject() {
        return bestSubject;
    }
    public Student (String firstName, String lastName, String bestSubject){
        this.firstName = firstName;
        this.lastName = lastName;
        this.bestSubject = bestSubject;
    }

    @Override
    public String toString() {
        return "Student: " + this.firstName +" " + this.lastName + ", " + this.bestSubject;
    }
}
