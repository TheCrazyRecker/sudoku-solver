package models;

public class Box {

    private int[][] box;

    public Box(int[][] box) {
        this.box = box;
    }

    public int[][] getBox() {
        return box;
    }

    public int getElement(int i, int j) {
        return box[i][j];
    }

    public void setElement(int i, int j, int element) {
        box[i][j] = element;
    }

    public boolean elementIsFilled(int i, int j) {
        return box[i][j] != 0;
    }

    public boolean boxIsFilled() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(!elementIsFilled(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return box[0][0] + " | " + box[0][1] + " | " + box[0][2] + "\n"
                + "---------\n"
                + box[1][0] + " | " + box[1][1] + " | " + box[1][2] + "\n"
                + "---------\n"
                + box[2][0] + " | " + box[2][1] + " | " + box[2][2] + "\n";
    }
}
