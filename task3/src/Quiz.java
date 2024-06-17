import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Quiz {
    private List<Question> questions;
    private int score;
    private List<Boolean> correctAnswers;
    private Scanner scanner;

    public Quiz() {
        questions = new ArrayList<>();
        correctAnswers = new ArrayList<>();
        score = 0;
        scanner = new Scanner(System.in);
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void start() {
        for (Question question : questions) {
            boolean answeredCorrectly = askQuestion(question);
            correctAnswers.add(answeredCorrectly);
            if (answeredCorrectly) {
                score++;
            }
        }
        displayResults();
    }

    private boolean askQuestion(Question question) {
        System.out.println(question.getQuestionText());
        String[] options = question.getOptions();
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }

        Timer timer = new Timer();
        QuizTask task = new QuizTask();
        timer.schedule(task, 10000); // 10 seconds for each question

        int answerIndex = -1;
        boolean validAnswer = false;
        while (!validAnswer && !task.isTimedOut()) {
            try {
                System.out.print("Please select an option (1-4): ");
                answerIndex = Integer.parseInt(scanner.nextLine()) - 1;
                validAnswer = answerIndex >= 0 && answerIndex < options.length;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
            }
        }

        timer.cancel();
        return question.isCorrectAnswer(answerIndex);
    }

    private void displayResults() {
        System.out.println("Quiz finished!");
        System.out.println("Your score: " + score + "/" + questions.size());
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println((i + 1) + ". " + question.getQuestionText());
            System.out.println("Correct answer: " + (question.getCorrectAnswerIndex() + 1));
            System.out.println("Your answer was: " + (correctAnswers.get(i) ? "Correct" : "Incorrect"));
        }
    }

    private class QuizTask extends TimerTask {
        private boolean timedOut = false;

        @Override
        public void run() {
            System.out.println("Time's up!");
            timedOut = true;
        }

        public boolean isTimedOut() {
            return timedOut;
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Quiz Game");
        Quiz quiz = new Quiz();
        quiz.addQuestion(new Question("What is the capital of France?", new String[]{"Berlin", "Paris", "Madrid", "Rome"}, 1));
        quiz.addQuestion(new Question("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, 1));
        quiz.addQuestion(new Question("What is the capital of Spain?", new String[]{"Lisbon", "Madrid", "Paris", "Berlin"}, 1));
        quiz.addQuestion(new Question("What is 5 * 3?", new String[]{"15", "20", "25", "30"}, 0));
        quiz.addQuestion(new Question("What is the square root of 16?", new String[]{"2", "3", "4", "5"}, 2));
        quiz.start();
    }
}
