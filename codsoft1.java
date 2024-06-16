import java.util.Scanner;
class Student
   {
    String name;
    int[] marks;
    double average;
    char grade;
    Student(String name, int numSubjects)
    {
        this.name = name;
        this.marks = new int[numSubjects];
    }
    void calculateAverage()
    {
        int total = 0;
        for (int mark : marks)
        {
            total += mark;
        }
        this.average = total / (double) marks.length;
    }
    void determineGrade()
    {
        if (average >= 90)
        {
            grade = 'A';
        } else if (average >= 80)
        {
            grade = 'B';
        } else if (average >= 70)
        {
            grade = 'C';
        } else if (average >= 60)
        {
            grade = 'D';
        } else
        {
            grade = 'F';
        }
    }
}
class StudentGradeCalculator
{
    public static void main(String[] args)
    {
        System.out.println("\nWelcome to Student Grade Calculator");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of subjects:");
        int numSubjects = scanner.nextInt();
        System.out.println("Enter the number of students:");
        int numStudents = scanner.nextInt();
        scanner.nextLine();
        Student[] students = new Student[numStudents];
        for (int i = 0; i < numStudents; i++)
        {
            System.out.println("Enter Student name:");
            String name = scanner.nextLine();
            students[i] = new Student(name, numSubjects);
            for (int j = 0; j < numSubjects; j++)
            {
                System.out.println("Enter the mark scored in subject " + (j + 1) + " for " + name + ":");
                students[i].marks[j] = scanner.nextInt();
            }
            scanner.nextLine();
            students[i].calculateAverage();
            students[i].determineGrade();
        }
        displayStudentGradeReport(students, numSubjects);
        scanner.close();
    }
    public static void displayStudentGradeReport(Student[] students, int numSubjects)
    {
        System.out.println("\nStudent Grade Report:");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-15s |", "Name");
        for (int j = 0; j < numSubjects; j++)
        {
            System.out.printf(" %-18s |", "Subject " + (j + 1) + " Marks");
        }
        System.out.printf(" %-15s | %-12s |\n", "Average Marks", "Grade");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        for (Student student : students)
        {
            System.out.printf("| %-15s |", student.name);
            for (int j = 0; j < numSubjects; j++)
            {
                System.out.printf(" %-18d |", student.marks[j]);
            }
            System.out.printf(" %-15.2f | %-12c |\n", student.average, student.grade);
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
    }
}
