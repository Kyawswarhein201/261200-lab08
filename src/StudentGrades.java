
import java.util.Scanner;

public class StudentGrades {

    // Calculate the average of student scores
    public static double calculateAverage(int[] scores) {

        // Check if the scores array is null
        if (scores == null) {
            throw new IllegalArgumentException(
                    "Scores array cannot be null.");
        }

        // Check if the scores array is empty
        if (scores.length == 0) {
            throw new IllegalArgumentException(
                    "Scores array cannot be empty.");
        }

        int total = 0;

        // Validate each score
        for (int score : scores) {

            if (score < 0) {
                throw new IllegalArgumentException(
                        "Score cannot be less than 0.");
            }

            if (score > 100) {
                throw new IllegalArgumentException(
                        "Score cannot be greater than 100.");
            }

            total += score;
        }

        // Calculate average using double division
        double average = (double) total / scores.length;

        // Throw a custom exception if average is below 40
        if (average < 40) {
            throw new FailedSignificantlyException(
                    "Student average is " + average
                            + ", which is a significant failure.");
        }

        return average;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            // Ask user to enter scores
            System.out.print("Enter scores (comma-separated): ");

            String input = scanner.nextLine().trim();

            // Handle empty input
            if (input.isEmpty()) {
                throw new IllegalArgumentException(
                        "Scores array cannot be empty.");
            }

            // Split scores using commas
            String[] parts = input.split(",", -1);

            // Create an integer array
            int[] scores = new int[parts.length];

            // Convert strings into integers
            for (int i = 0; i < parts.length; i++) {
                scores[i] = Integer.parseInt(parts[i].trim());
            }

            // Calculate average
            double average = calculateAverage(scores);

            // Display result
            System.out.println("Average score: " + average);

        } catch (NumberFormatException e) {

            // Handle non-numeric input
            System.out.println(
                    "Invalid input: Please enter a valid number for scores. "
                            + e.getMessage());

        } catch (FailedSignificantlyException e) {

            // Handle a significantly low average
            System.out.println(
                    "Significant Failure: " + e.getMessage());

        } catch (IllegalArgumentException e) {

            // Handle invalid scores or empty input
            System.out.println("Error: " + e.getMessage());

        } finally {

            // Always close Scanner and print final message
            scanner.close();

            System.out.println(
                    "Grade calculation process concluded.");
        }
    }
}
