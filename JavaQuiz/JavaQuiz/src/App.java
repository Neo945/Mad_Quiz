import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("------------Quiz------------");
        System.out.println("1. Maths");
        System.out.println("2. Java");
        System.out.println("3. Dart");
        System.out.print("Enter your Test Code: ");
        Scanner scanner = new Scanner(System.in);
        int whichTest = scanner.nextInt();
        switch (whichTest) {
            case 1:
            new MathQuiz(scanner);
            break;
            case 2:
            new JavaQuiz(scanner);
            break;
            case 3:
            new DartQuiz(scanner);
            break;
            default:
                System.out.println("No test found!!");
        }
        scanner.close();
    }
}
