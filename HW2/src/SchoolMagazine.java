import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SchoolMagazine {

    private static List<Student> students = new ArrayList<>();

    public static int getSize() {
        return students.size();
    }

    static {
        fillMagazine();
    }

    public static List<Student> getStudents() {
        return students;
    }

    public void getStudentsWithGrades() {
        for (Student student : students) {
            if (student.hasGrade()) {
                System.out.println(student + ": " + student.getGrade());
            }
        }
    }

    public static void fillMagazine() {
        try (FileReader fileReader = new FileReader("./src/Students.txt");
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String firstAndLastName;
            while (bufferedReader.ready()) {
                firstAndLastName = bufferedReader.readLine();
                String[] names = firstAndLastName.split(" ");
                Student student = new Student();
                student.setFirstName(names[1]);
                student.setLastName(names[0]);

                students.add(student);
            }
        } catch (IOException ex) {
            System.out.println("Файл не найден");
        }
    }
}