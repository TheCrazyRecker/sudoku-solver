package models;

import java.util.Set;

public class Sudoku {

    private final int[][] sudoku;

    public Sudoku(int[][] sudoku) {
        this.sudoku = sudoku;
    }

    public int[][] getSudoku() {
        return sudoku;
    }

    public boolean elementIsFilled(int i, int j) {
        return sudoku[i][j] != 0;
    }

    public boolean isElement(int i, int j, int element) {
        return sudoku[i][j] == element;
    }

    public void setElement(int i, int j, int element, boolean elimination, boolean debug) {
        sudoku[i][j] = element;
        if(debug) {
            System.out.println("\u001B[32m" + this);
            System.out.println("\u001B[31m");
            System.out.println("Element " + element + " has been inserted in position " +
                    "(" + (i + 1) + ", " + (j + 1) + ") by " + (elimination ? "elimination" : "exhaustion"));
            System.out.println("\u001B[0m");
        }
    }

    public int getElement(int i, int j) {
        return sudoku[i][j];
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(sudoku[i][j]).append(j == 8 ? "" : (j == 2 || j == 5) ? " || " : " | ");
            }
            sb.append("\n");
            String seperator = "------------------------------------\n";
            if (i == 2 || i == 5) sb.append(seperator);
        }
        String result = sb.toString();
        return result.replaceAll("0", " ");
    }

    public String markElements(Set<Tuple> markedElements) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (markedElements.contains(new Tuple(i, j))) {
                    sb.append("X");
                } else {
                    sb.append(sudoku[i][j]);
                }
                sb.append(j == 8 ? "" : (j == 2 || j == 5) ? " || " : " | ");
            }
            sb.append("\n");
            String seperator = "------------------------------------\n";
            if (i == 2 || i == 5) sb.append(seperator);
        }
        String result = sb.toString();
        return result.replaceAll("0", " ");
    }

    public static Sudoku easy() {
        int[] first = {0, 0, 7, 5, 3, 0, 0, 0, 0};
        int[] second = {0, 1, 0, 0, 4, 0, 7, 2, 5};
        int[] third = {0, 0, 0, 8, 2, 7, 0, 6, 0};

        int[] fourth = {4, 5, 1, 9, 6, 0, 8, 0, 0};
        int[] fifth = {0, 3, 6, 0, 0, 0, 0, 4, 0};
        int[] sixth = {0, 0, 0, 0, 7, 4, 0, 0, 0};

        int[] seventh = {0, 7, 5, 0, 0, 6, 0, 0, 3};
        int[] eighth = {3, 0, 9, 2, 0, 8, 5, 0, 0};
        int[] ninth = {2, 4, 8, 0, 5, 3, 0, 1, 0};

        int[][] sudokuArr = {first, second, third, fourth, fifth, sixth, seventh, eighth, ninth};
        return new Sudoku(sudokuArr);
    }

    public static Sudoku medium() {
        int[] first = {4, 0, 3, 0, 0, 0, 1, 0, 5};
        int[] second = {0, 6, 0, 9, 0, 0, 0, 0, 0};
        int[] third = {0, 0, 0, 0, 1, 0, 0, 4, 6};

        int[] fourth = {9, 3, 2, 0, 8, 1, 0, 7, 4};
        int[] fifth = {0, 0, 0, 0, 9, 7, 3, 0, 0};
        int[] sixth = {0, 1, 4, 0, 2, 6, 9, 5, 8};

        int[] seventh = {0, 4, 7, 8, 3, 0, 0, 0, 0};
        int[] eighth = {0, 0, 0, 0, 4, 0, 5, 0, 0};
        int[] ninth = {6, 0, 9, 1, 0, 0, 0, 8, 0};

        int[][] sudokuArr = {first, second, third, fourth, fifth, sixth, seventh, eighth, ninth};
        return new Sudoku(sudokuArr);
    }

    public static Sudoku hard() {
        int[] first = {0, 0, 0, 5, 0, 0, 8, 0, 0};
        int[] second = {2, 5, 7, 0, 0, 0, 0, 4, 9};
        int[] third = {0, 8, 0, 0, 0, 0, 0, 0, 1};

        int[] fourth = {3, 0, 0, 0, 0, 0, 0, 6, 0};
        int[] fifth = {0, 2, 9, 7, 0, 3, 0, 0, 0};
        int[] sixth = {5, 0, 0, 0, 0, 8, 3, 0, 4};

        int[] seventh = {0, 0, 2, 1, 0, 0, 0, 9, 0};
        int[] eighth = {7, 4, 0, 0, 0, 0, 0, 0, 0};
        int[] ninth = {1, 0, 6, 0, 8, 4, 7, 0, 3};

        int[][] sudokuArr = {first, second, third, fourth, fifth, sixth, seventh, eighth, ninth};
        return new Sudoku(sudokuArr);
    }

    public static Sudoku expert() {
        int[] first = {0, 2, 7, 0, 0, 0, 6, 0, 0};
        int[] second = {0, 0, 0, 6, 0, 0, 4, 0, 0};
        int[] third = {6, 0, 0, 2, 0, 0, 0, 1, 7};

        int[] fourth = {0, 3, 0, 0, 0, 0, 0, 0, 0};
        int[] fifth = {0, 0, 0, 5, 0, 0, 1, 0, 0};
        int[] sixth = {5, 0, 8, 1, 6, 0, 3, 0, 0};

        int[] seventh = {8, 7, 1, 3, 4, 5, 0, 0, 6};
        int[] eighth = {0, 0, 9, 0, 0, 0, 5, 0, 1};
        int[] ninth = {0, 0, 0, 8, 0, 1, 0, 4, 0};

        int[][] sudokuArr = {first, second, third, fourth, fifth, sixth, seventh, eighth, ninth};
        return new Sudoku(sudokuArr);
    }

    public static Sudoku master() {
        int[] first = {0, 0, 0, 0, 0, 6, 0, 0, 0};
        int[] second = {0, 0, 2, 0, 3, 5, 0, 8, 0};
        int[] third = {0, 0, 0, 0, 2, 0, 0, 1, 3};

        int[] fourth = {0, 2, 0, 0, 0, 0, 1, 9, 0};
        int[] fifth = {9, 3, 0, 0, 0, 0, 7, 0, 0};
        int[] sixth = {5, 0, 0, 0, 0, 4, 0, 0, 0};

        int[] seventh = {1, 0, 0, 0, 0, 0, 0, 7, 0};
        int[] eighth = {0, 7, 0, 0, 0, 1, 4, 2, 0};
        int[] ninth = {0, 9, 0, 4, 0, 3, 0, 0, 0};

        int[][] sudokuArr = {first, second, third, fourth, fifth, sixth, seventh, eighth, ninth};
        return new Sudoku(sudokuArr);
    }

    public static Sudoku extreme() {
        int[] first = {0, 0, 9, 0, 5, 0, 0, 0, 0};
        int[] second = {5, 3, 0, 0, 8, 4, 0, 2, 0};
        int[] third = {0, 0, 0, 6, 0, 0, 0, 0, 4};

        int[] fourth = {4, 0, 6, 0, 3, 0, 0, 0, 0};
        int[] fifth = {0, 1, 8, 0, 0, 0, 0, 0, 9};
        int[] sixth = {0, 0, 0, 0, 0, 0, 2, 0, 0};

        int[] seventh = {0, 0, 0, 0, 0, 0, 0, 5, 0};
        int[] eighth = {0, 8, 0, 0, 0, 0, 7, 0, 0};
        int[] ninth = {0, 0, 0, 5, 6, 1, 9, 0, 0};

        int[][] sudokuArr = {first, second, third, fourth, fifth, sixth, seventh, eighth, ninth};
        return new Sudoku(sudokuArr);
    }
}
