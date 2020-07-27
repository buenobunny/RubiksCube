//Contestant #1743
package com.skillsusa.rubikscube.solver;

public class Move {

    private String moveName;
    private int col;
    private int row;

    /**
     * just an easier way to keep track of my moves.
     * @param moveName these
     * @param col are
     * @param row self-explanatory
     */
    public Move(String moveName, int col, int row) {
        this.moveName = moveName;
        this.col = col;
        this.row = row;
    }

    public String getMoveName() {
        return moveName;
    }

    public void setMoveName(String moveName) {
        this.moveName = moveName;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    /**
     * override of the standard equals function. better for what I'm doing.
     * @param comparation
     * @return
     */
    public boolean equals(Move comparation) {
        if (comparation.getMoveName().equals(moveName) && comparation.getCol() == col && comparation.getRow()==row) return true;
        return false;
    }
}
