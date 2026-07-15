import java.util.Scanner;

public class QuizGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int score = 0;

        System.out.println("Welcome to the Quiz Game!");

        System.out.print("1. What is the capital of France? ");
        String answer1 = input.nextLine();
        if (answer1.equalsIgnoreCase("Paris")) {
            score++;
        }

        System.out.print("2. What is 5 + 5? ");
        int answer2 = input.nextInt();
        if (answer2 == 10) {
            score++;
        }

        input.nextLine();

        System.out.print("3. What color is the sky on a clear day? ");
        String answer3 = input.nextLine();
        if (answer3.equalsIgnoreCase("Blue")) {
            score++;
        }

        System.out.println("Your final score is: " + score + " out of 3");

        input.close();
    }
}
