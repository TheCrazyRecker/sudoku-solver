import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            Sudoku sudoku = null;
            while (sudoku == null) {
                System.out.println("Select a difficulty: ");
                System.out.println("E (Easy) | M (Medium) | H (Hard)");
                String input = scanner.next();
                switch (input) {
                    case "e", "E" -> sudoku = Sudoku.easy();
                    case "m", "M" -> sudoku = Sudoku.medium();
                    case "h", "H" -> sudoku = Sudoku.hard();
                    default -> System.out.println("Please select a valid difficulty.");
                }
            }
            int iterations = 0;
            while (iterations == 0) {
                System.out.println("Select a number of solving iterations: ");
                String input = scanner.next();
                try {
                    iterations = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Please select a valid number of iterations.");
                }
            }
            SudokuSolver solver = new SudokuSolver(sudoku);
            solver.solve(iterations);

            System.out.println("Would you like to solve another one? (Y/N)");
            String input = scanner.next();
            if(input.equalsIgnoreCase("n")) {
                break;
            }
        }
    }
}