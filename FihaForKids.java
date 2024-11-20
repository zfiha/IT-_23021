import java.io.*;
import java.util.*;

public class FihaForKids {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputFile = "input.txt"; 
        String outputFile = "output.txt"; 
        int score = 0;

                System.out.println("Welcome to the Math Quiz!");
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();

                System.out.print("Do you want to play? (yes/no): ");
        String playChoice = scanner.nextLine().toLowerCase();
        if (!playChoice.equals("yes")) {
            System.out.println("Goodbye! Maybe next time.");
            return;
        }

                System.out.println("Select a difficulty level:");
        System.out.println("1. Easy (5 questions)");
        System.out.println("2. Medium (10 questions)");
        System.out.println("3. Hard (15 questions)");
        System.out.print("Enter your choice (1/2/3): ");
        int difficulty = scanner.nextInt();
        int questionLimit = getQuestionLimit(difficulty);

               try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            int questionCount = 0;

            while ((line = reader.readLine()) != null && questionCount < questionLimit) {
                String[] parts = line.split("=");
                if (parts.length != 2) continue;

                String question = parts[0].trim();
                int correctAnswer = Integer.parseInt(parts[1].trim());

                
                System.out.println("What is: " + question);
                int userAnswer = scanner.nextInt();

               
                if (userAnswer == correctAnswer) {
                    System.out.println("Correct!");
                    score++;
                } else {
                    System.out.println("Wrong! The correct answer is: " + correctAnswer);
                }
                questionCount++;
            }

            
            System.out.println("Quiz Over! Your score: " + score + "/" + questionLimit);

            
            saveResult(playerName, score, questionLimit, outputFile);

        } catch (IOException e) {
            System.out.println("Error reading the question file.");
        }
    }

        private static int getQuestionLimit(int difficulty) {
        switch (difficulty) {
            case 1: return 5;
            case 2: return 10;
            case 3: return 15;
            default: return 0;
        }
    }

       private static void saveResult(String playerName, int score, int questionLimit, String outputFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true))) {
            writer.write(playerName + " - Score: " + score + "/" + questionLimit);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing the result to the file.");
        }
    }
}