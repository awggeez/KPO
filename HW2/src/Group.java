import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Group {
    public static void main(String[] args) {
        System.out.println("""
                Выберите одну из следующих команд:
                /r - выбор случайного студента
                /l - список студентов с оценками
                /h - список возможных командy - узнать, был ли студент на паре
                /end - завершить пару
                """);

        Scanner scanner = new Scanner(System.in);
        String command = "";
        SchoolMagazine magazine = new SchoolMagazine();
        if (SchoolMagazine.getStudents().size() == 0) {
            return;
        }
        while (!command.equals("/end")) {
            System.out.print("> ");
            command = scanner.nextLine();
            System.out.println();
            List<Student> students = SchoolMagazine.getStudents();
            switch (command) {
                case "/r":
                    randomStudent(students);
                    break;
                case "/l":
                    magazine.getStudentsWithGrades();
                    System.out.println();
                    break;
                case "/h":
                    System.out.println("""
                            1. /r - выбор случайного студента
                            2. /l - список студентов с оценками
                            """);
                    break;
                case "/end":
                    break;
                default:
                    System.out.println("""
                            Некорректная команда
                            Выберите одну из следующих команд:
                            /r - выбор случайного студента
                            /l - список студентов с оценками
                            /h - список возможных командy - узнать, был ли студент на паре
                            /end - завершить пару
                            
                            """);
            }
        }
        try (FileWriter fileWriter = new FileWriter("./src/SchoolMagazine.txt");
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            List<Student> students = SchoolMagazine.getStudents();
            for (Student student : students) {
                System.out.print(student + " ");
                if (student.getGrade() == 0) {
                    System.out.println("-");
                    writer.write(student.getFirstName() + " " + student.getLastName() + " | - | Присутствие на паре: " +
                                 student.isPresence() + "\n\n");
                } else {
                    System.out.println(student.getGrade());
                    writer.write(student.getFirstName() + " " + student.getLastName() + " | Оценка: " + student.getGrade() +
                                 " | Присутствие на паре: " + student.isPresence() + "\n\n");
                }
            }
        } catch (Exception ex) {
            System.out.println("Файл не найден");
        }
    }

    public static void randomStudent(List<Student> students) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int index = random.nextInt(SchoolMagazine.getSize());
        Student student = students.get(index);
        if (student.hasGrade()) {
            System.out.println("Данный студент уже был выбран. Выберите другого");
        } else {
            System.out.println("> Отвечает " + student);

            boolean isCorrectAnswer = false;
            while (!isCorrectAnswer) {
                System.out.println("Присутствует на паре?");
                System.out.print("> ");
                String answer = scanner.nextLine();
                switch (answer) {
                    case "y":
                        System.out.println();
                        student.setPresence(true);
                        student.setHasGrade(true);
                        student.setGrade(1 + random.nextInt(10));
                        System.out.println("> Оценка за ответ: " + student.getGrade() + "\n");
                        isCorrectAnswer = true;
                        break;
                    case "n":
                        System.out.println();
                        student.setPresence(false);
                        isCorrectAnswer = true;
                        break;
                    default:
                        System.out.println("""
                                Некорректный ответ
                                Выберите либо "y" - присутсвует, либо "n" - отсутствует
                                
                                """);
                }
            }
        }
    }
}