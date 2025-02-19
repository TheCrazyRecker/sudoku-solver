import models.Box;
import models.Sudoku;
import models.Tuple;
import java.util.*;

public class SudokuSolver {

    private final Sudoku sudoku;
    private final Map<Box, Map<Tuple, Set<Integer>>> map;
    private final Set<Integer> possibleNumbers = new HashSet<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));

    public SudokuSolver(Sudoku sudoku) {
        this.sudoku = sudoku;
        this.map = new HashMap<>();
        initializeMap();
    }

    private void initializeMap() {

        for (Box[] boxRow : sudoku.getSudoku()) {
            for (Box box : boxRow) {
                Map<Tuple, Set<Integer>> boxMap = new HashMap<>();
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        boxMap.put(new Tuple(i, j), new HashSet<>(possibleNumbers));
                    }
                }
                map.put(box, boxMap);
            }
        }
        updateLegalMoves();
    }

    private void updateLegalMoves() {
        for (Box[] boxRow : sudoku.getSudoku()) {
            for (Box box : boxRow) {
                checkBox(box);
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                checkRow(i);
                checkColumn(j);
            }
        }
    }

    private void checkBox(Box box) {
        Map<Tuple, Set<Integer>> boxMap = map.get(box);
        Set<Integer> numbersInBox = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (box.elementIsFilled(i, j)) {
                    numbersInBox.add(box.getElement(i, j));
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!box.elementIsFilled(i, j)) {
                    Set<Integer> options = boxMap.get(new Tuple(i, j));
                    options.removeAll(numbersInBox);
                    boxMap.put(new Tuple(i, j), options);
                }
            }
        }
    }

    private void checkRow(int i) {
        Set<Integer> numbersInBox = new HashSet<>();

        int idx;
        if (i < 3) {
            idx = 0;
        } else if (i < 6) {
            i -= 3;
            idx = 1;
        } else {
            i -= 6;
            idx = 2;
        }

        Box[] boxRow = sudoku.getSudoku()[idx];
        for (Box box : boxRow) {
            for (int j = 0; j < 3; j++) {
                numbersInBox.add(box.getElement(i, j));
            }
        }

        for (Box box : boxRow) {
            Map<Tuple, Set<Integer>> boxMap = map.get(box);
            for (int j = 0; j < 3; j++) {
                Set<Integer> options = boxMap.get(new Tuple(i, j));
                options.removeAll(numbersInBox);
                boxMap.put(new Tuple(i, j), options);
            }
        }
    }

    public void checkColumn(int j) {
        Set<Integer> numbersInBox = new HashSet<>();

        int idx;
        if (j < 3) {
            idx = 0;
        } else if (j < 6) {
            j -= 3;
            idx = 1;
        } else {
            j -= 6;
            idx = 2;
        }

        Box[][] boxGrid = sudoku.getSudoku();
        Box[] boxColumn = new Box[boxGrid.length];

        for (int i = 0; i < boxGrid.length; i++) {
            boxColumn[i] = boxGrid[i][idx];
        }

        for (Box box : boxColumn) {
            for (int i = 0; i < 3; i++) {
                numbersInBox.add(box.getElement(i, j));
            }
        }

        for (Box box : boxColumn) {
            Map<Tuple, Set<Integer>> boxMap = map.get(box);
            for (int i = 0; i < 3; i++) {
                Set<Integer> options = boxMap.get(new Tuple(i, j));
                options.removeAll(numbersInBox);
                boxMap.put(new Tuple(i, j), options);
            }
        }
    }

    private boolean isSolved() {
        for (Box[] row : sudoku.getSudoku()) {
            for (Box box : row) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (!box.elementIsFilled(i, j)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private void fillElements() {
        for (Box[] row : sudoku.getSudoku()) {
            for (Box box : row) {
                Map<Tuple, Set<Integer>> boxMap = map.get(box);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (!box.elementIsFilled(i, j)) {
                            Set<Integer> options = boxMap.get(new Tuple(i, j));
                            if (options.size() == 1) {
                                box.setElement(i, j, (Integer) options.toArray()[0]);
                            }
                        }
                    }
                }
            }
        }
    }

    public void solve(int maxIterations) {
        for (int i = 0; i < maxIterations; i++) {
            System.out.println(sudoku);
            fillElements();
            updateLegalMoves();
        }
        System.out.println(sudoku);
        System.out.println("Solved: " + isSolved());

    }
}
