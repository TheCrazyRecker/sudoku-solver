package models;

public class Sudoku {

    private Box[][] sudoku;

    public Sudoku(Box[][] sudoku) {
        this.sudoku = sudoku;
    }

    public Box[][] getSudoku() {
        return sudoku;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            Box[] row = sudoku[i];
            int[][] box1 = row[0].getBox();
            int[][] box2 = row[1].getBox();
            int[][] box3 = row[2].getBox();
            String rowString =
                    box1[0][0] + " | " + box1[0][1] + " | " + box1[0][2] + " || " + box2[0][0] + " | " + box2[0][1] + " | " + box2[0][2] + " || " + box3[0][0] + " | " + box3[0][1] + " | " + box3[0][2] + "\n"
                    + box1[1][0] + " | " + box1[1][1] + " | " + box1[1][2] + " || " + box2[1][0] + " | " + box2[1][1] + " | " + box2[1][2] + " || " + box3[1][0] + " | " + box3[1][1] + " | " + box3[1][2] + "\n"
                    + box1[2][0] + " | " + box1[2][1] + " | " + box1[2][2] + " || " + box2[2][0] + " | " + box2[2][1] + " | " + box2[2][2] + " || " + box3[2][0] + " | " + box3[2][1] + " | " + box3[2][2] + "\n";
            String seperator = "-----------------------------------\n";
            sb.append(rowString);
            if (i != 2) sb.append(seperator);
        }
        String result = sb.toString();
        return result.replaceAll("0", " ");
    }

    public static Sudoku easy() {
        int[][] topLeftArr = {{0, 0, 7}, {0, 1, 0}, {0, 0, 0}};
        Box topLeft = new Box(topLeftArr);

        int[][] topMiddleArr = {{5, 3, 0}, {0, 4, 0}, {8, 2, 7}};
        Box topMiddle = new Box(topMiddleArr);

        int[][] topRightArr = {{0, 0, 0}, {7, 2, 5}, {0, 6, 0}};
        Box topRight = new Box(topRightArr);

        int[][] middleLeftArr = {{4, 5, 1}, {0, 3, 6}, {0, 0, 0}};
        Box middleLeft = new Box(middleLeftArr);

        int[][] middleMiddleArr = {{9, 6, 0}, {0, 0, 0}, {0, 7, 4}};
        Box middleMiddle = new Box(middleMiddleArr);

        int[][] middleRightArr = {{8, 0, 0}, {0, 4, 0}, {0, 0, 0}};
        Box middleRight = new Box(middleRightArr);

        int[][] bottomLeftArr = {{0, 7, 5}, {3, 0, 9}, {2, 4, 8}};
        Box bottomLeft = new Box(bottomLeftArr);

        int[][] bottomMiddleArr = {{0, 0, 6}, {2, 0, 8}, {0, 5, 3}};
        Box bottomMiddle = new Box(bottomMiddleArr);

        int[][] bottomRightArr = {{0, 0, 3}, {5, 0, 0}, {0, 1, 0}};
        Box bottomRight = new Box(bottomRightArr);

        Box[][] sudokuArr = {
                {topLeft, topMiddle, topRight},
                {middleLeft, middleMiddle, middleRight},
                {bottomLeft, bottomMiddle, bottomRight}
        };
        return new Sudoku(sudokuArr);
    }

    public static Sudoku medium() {
        int[][] topLeftArr = {{3, 1, 0}, {5, 6, 0}, {2, 4, 0}};
        Box topLeft = new Box(topLeftArr);

        int[][] topMiddleArr = {{4, 8, 0}, {0, 0, 0}, {7, 3, 5}};
        Box topMiddle = new Box(topMiddleArr);

        int[][] topRightArr = {{9, 2, 5}, {0, 0, 7}, {6, 8, 0}};
        Box topRight = new Box(topRightArr);

        int[][] middleLeftArr = {{8, 0, 0}, {7, 0, 2}, {0, 0, 4}};
        Box middleLeft = new Box(middleLeftArr);

        int[][] middleMiddleArr = {{0, 0, 0}, {8, 6, 0}, {0, 5, 1}};
        Box middleMiddle = new Box(middleMiddleArr);

        int[][] middleRightArr = {{0, 0, 9}, {0, 0, 0}, {2, 0, 0}};
        Box middleRight = new Box(middleRightArr);

        int[][] bottomLeftArr = {{0, 0, 0}, {4, 0, 0}, {0, 0, 5}};
        Box bottomLeft = new Box(bottomLeftArr);

        int[][] bottomMiddleArr = {{0, 7, 8}, {0, 0, 0}, {1, 0, 0}};
        Box bottomMiddle = new Box(bottomMiddleArr);

        int[][] bottomRightArr = {{0, 9, 4}, {8, 1, 0}, {0, 0, 0}};
        Box bottomRight = new Box(bottomRightArr);

        Box[][] sudokuArr = {
                {topLeft, topMiddle, topRight},
                {middleLeft, middleMiddle, middleRight},
                {bottomLeft, bottomMiddle, bottomRight}
        };
        return new Sudoku(sudokuArr);
    }

    public static Sudoku hard() {
        int[][] topLeftArr = {{8, 0, 0}, {6, 0, 0}, {5, 0, 0}};
        Box topLeft = new Box(topLeftArr);

        int[][] topMiddleArr = {{0, 0, 0}, {3, 0, 1}, {2, 0, 0}};
        Box topMiddle = new Box(topMiddleArr);

        int[][] topRightArr = {{0, 5, 7}, {9, 0, 0}, {0, 0, 0}};
        Box topRight = new Box(topRightArr);

        int[][] middleLeftArr = {{1, 0, 7}, {0, 0, 0}, {3, 9, 0}};
        Box middleLeft = new Box(middleLeftArr);

        int[][] middleMiddleArr = {{0, 4, 0}, {0, 0, 0}, {1, 2, 8}};
        Box middleMiddle = new Box(middleMiddleArr);

        int[][] middleRightArr = {{8, 0, 0}, {0, 9, 0}, {0, 4, 0}};
        Box middleRight = new Box(middleRightArr);

        int[][] bottomLeftArr = {{0, 0, 5}, {9, 0, 8}, {0, 6, 3}};
        Box bottomLeft = new Box(bottomLeftArr);

        int[][] bottomMiddleArr = {{6, 0, 2}, {0, 0, 0}, {8, 0, 0}};
        Box bottomMiddle = new Box(bottomMiddleArr);

        int[][] bottomRightArr = {{0, 0, 0}, {5, 0, 1}, {0, 0, 0}};
        Box bottomRight = new Box(bottomRightArr);

        Box[][] sudokuArr = {
                {topLeft, topMiddle, topRight},
                {middleLeft, middleMiddle, middleRight},
                {bottomLeft, bottomMiddle, bottomRight}
        };
        return new Sudoku(sudokuArr);
    }
}
