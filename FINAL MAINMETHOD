import java.util.Scanner;
/**
 * Project 04 -- MainMethod
 * <p>
 * Extends the run method from login to organize which class is called
 *
 * @author Tim Chou L09
 * @version April 11, 2022
 */

public class MainMethod extends Run {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String username = runQuiz(s);
        String[] splited = username.split(" ");
        if (splited[0].equals("Teacher")) {
            Teacher teacher = new Teacher(splited[1]);
            teacher.teacherInterface(s);
        } else if (splited[0].equals("Student")) {
            Student student = new Student(splited[1]);
            student.mainStudent(s);
        }
    }
}
