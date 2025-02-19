import models.Sudoku;
import models.Tuple;
import java.util.*;

public class SudokuSolver {

    private final Sudoku sudoku;
    private final Map<Tuple, Set<Integer>> map;
    private final Set<Integer> possibleNumbers = new HashSet<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
    private boolean debug = false;

    public SudokuSolver(Sudoku sudoku) {
        this.sudoku = sudoku;
        this.map = new HashMap<>();
        initializeMap();
    }

    private void initializeMap() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                map.put(new Tuple(i, j), new HashSet<>(possibleNumbers));
            }
        }
        updateLegalMoves();
    }

    private void updateLegalMoves() {
        checkBoxes();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                checkRow(i);
                checkColumn(j);
            }
        }
    }

    private void checkBoxes() {
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                Set<Integer> numbersInBox = new HashSet<>();
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        numbersInBox.add(sudoku.getElement(i + k, j + l));
                    }
                }

                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        if (!sudoku.elementIsFilled(i, j)) {
                            Tuple tuple = new Tuple(i + k, j + l);
                            Set<Integer> options = map.get(tuple);
                            options.removeAll(numbersInBox);
                            map.put(tuple, options);
                        }
                    }
                }
            }
        }
    }

    private void checkRow(int i) {
        Set<Integer> numbersInRow = new HashSet<>();
        for (int j = 0; j < 9; j++) {
            numbersInRow.add(sudoku.getElement(i, j));
        }
        for (int j = 0; j < 9; j++) {
            Tuple tuple = new Tuple(i, j);
            Set<Integer> options = map.get(tuple);
            options.removeAll(numbersInRow);
            map.put(tuple, options);
        }
    }

    public void checkColumn(int j) {
        Set<Integer> numbersInColumn = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            numbersInColumn.add(sudoku.getElement(i, j));
        }
        for (int i = 0; i < 9; i++) {
            Tuple tuple = new Tuple(i, j);
            Set<Integer> options = map.get(tuple);
            options.removeAll(numbersInColumn);
            map.put(tuple, options);
        }
    }

    private boolean isSolved() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!sudoku.elementIsFilled(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void fillElements() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!sudoku.elementIsFilled(i, j)) {
                    Tuple tuple = new Tuple(i, j);
                    Set<Integer> options = map.get(tuple);
                    if (options.size() == 1) {
                        sudoku.setElement(i, j, (Integer) options.toArray()[0], false, debug);
                    }
                }
            }
        }
    }

    private void eliminate() {
        for (int num : possibleNumbers) {
            Set<Tuple> eliminated = findEliminated(num);
            Set<Tuple> freeIndices = findAllEmptySquares();
            freeIndices.removeAll(eliminated);
            fillElementsByElimination(freeIndices, num);
        }
    }

    private Set<Tuple> findEliminated(int num) {
        Set<Tuple> eliminated = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku.isElement(i, j, num)) {
                    for (int k = 0; k < 9; k++) {
                        if (!sudoku.elementIsFilled(i, k)) {
                            eliminated.add(new Tuple(i, k));
                        }
                        if (!sudoku.elementIsFilled(k, j)) {
                            eliminated.add(new Tuple(k, j));
                        }
                    }
                    int boxRow = (i - (i % 3));
                    int boxColumn = (j - (j % 3));
                    for (int k = boxRow; k < (boxRow + 3); k++) {
                        for (int l = boxColumn; l < (boxColumn + 3); l++) {
                            if (!sudoku.elementIsFilled(k, l)) {
                                eliminated.add(new Tuple(k, l));
                            }
                        }
                    }
                }
            }
        }
        return eliminated;
    }

    private Set<Tuple> findAllEmptySquares() {
        Set<Tuple> freeIndices = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!sudoku.elementIsFilled(i, j)) {
                    freeIndices.add(new Tuple(i, j));
                }
            }
        }
        return freeIndices;
    }

    private void fillElementsByElimination(Set<Tuple> freeIndices, int num) {
        Set<Tuple> eliminated = findEliminated(num);
        for (Tuple t : freeIndices) {
            boolean row = true;
            boolean column = true;
            boolean box = true;
            for (int k = 0; k < 9; k++) {
                if (!row && !column) {
                    break;
                }
                if (row) {
                    if (k != t.getJ()) {
                        if (sudoku.isElement(t.getI(), k, num)) {
                            row = false;
                        }
                        if (!sudoku.elementIsFilled(t.getI(), k)) {
                            if (!eliminated.contains(new Tuple(t.getI(), k))) {
                                row = false;
                            }
                        }
                    }
                }
                if (column) {
                    if (k != t.getI()) {
                        if (sudoku.isElement(k, t.getJ(), num)) {
                            column = false;
                        }
                        if (!sudoku.elementIsFilled(k, t.getJ())) {
                            if (!eliminated.contains(new Tuple(k, t.getJ()))) {
                                column = false;
                            }
                        }
                    }
                }
            }
            int boxRow = (t.getI() - (t.getI() % 3));
            int boxColumn = (t.getJ() - (t.getJ() % 3));
            for (int k = boxRow; k < (boxRow + 3); k++) {
                for (int l = boxColumn; l < (boxColumn + 3); l++) {
                    if (!box) {
                        break;
                    }
                    if (!(k == t.getI() && t.getJ() == l)) {
                        if (sudoku.isElement(k, l, num)) {
                            box = false;
                        }
                        if (!sudoku.elementIsFilled(k, l)) {
                            if (!eliminated.contains(new Tuple(k, l))) {
                                box = false;
                            }
                        }
                    }
                }
            }
            if (row || column || box) {
                sudoku.setElement(t.getI(), t.getJ(), num, true, debug);
                eliminated = findEliminated(num);
            }
        }
    }

    public void solve(int maxIterations, boolean debug) {
        this.debug = debug;
        for (int i = 0; i < maxIterations; i++) {
            System.out.println(sudoku);
            fillElements();
            eliminate();
            updateLegalMoves();
            if (isSolved()) {
                System.out.println(sudoku);
                System.out.println("Solved in " + i + " iterations.");
                break;
            }
        }
        if(!isSolved()) {
            System.out.println("The sudoku is not solvable within the entered amount of iterations.");
        }
    }
}
