//Contestant #1743
package com.skillsusa.rubikscube;

import java.awt.*;
import java.util.ArrayList;

public class RubiksSide {

    private int sideNumber;
    private ArrayList<RubiksPiece> rubiksPieces;

    /**
     * Sets up a side of the Rubik's Cube with a uniform color.
     * @param sideNumber
     * @param color
     */
    public RubiksSide(int sideNumber, Color color) {
        rubiksPieces = new ArrayList<>();
        this.sideNumber = sideNumber;
        for (int j = 1; j < 4; j++ ) {
            for (int k = 1; k < 4; k++) {
                rubiksPieces.add(new RubiksPiece(color, j, k));
            }
        }
    }

    /**
     * @return the side number for program use.
     */
    public int getSideNumber() {
        return sideNumber;
    }

    /**
     * returns a piece given its coordinates in a given side.
     * @param x The x coordinate ranging from 1-3
     * @param y the y coordinate ranging from 1-3
     * @return the correct cube piece. If not found, returns null
     */
    public RubiksPiece getPiece(int x, int y) {
        for (int i = 0; i < rubiksPieces.size(); i++) {
            if (rubiksPieces.get(i).getxPos() == x && rubiksPieces.get(i).getyPos() == y) return rubiksPieces.get(i);
        }
        return null;
    }

    /**
     * @param index
     * @return the correct cube piece given its index.
     */
    public RubiksPiece getPiece(int index) {
        return rubiksPieces.get(index);
    }

    /**
     * sets piece in the arraylist to the given piece
     * @param index place in the array to change it
     * @param rubiksPiece piece to change it to
     */
    public void setPiece(int index, RubiksPiece rubiksPiece) {
        rubiksPieces.set(index, rubiksPiece);
    }

    /**
     * sets a piece given its coordinates within the cube side
     * @param x the x ranging from 1-3
     * @param y the y ranging from 1-3
     * @param rubiksPiece the piece to be replaced with
     */
    public void setPiece(int x, int y, RubiksPiece rubiksPiece) {
        for (int i = 0; i < rubiksPieces.size(); i++) {
            if (rubiksPieces.get(i).getxPos() == x && rubiksPieces.get(i).getyPos() == y) rubiksPieces.set(i, rubiksPiece);
        }
    }

    /**
     * replaces all the pieces in the side. takes up space but easier to flip around.
     * @param newPieces
     */
    public void replacePieces(ArrayList<RubiksPiece> newPieces) {
        this.rubiksPieces = newPieces;
    }

    /**
     * returns the pieces to use
     * @return all the pieces on the side
     */
    public ArrayList<RubiksPiece> getAllPieces() {
        return rubiksPieces;
    }

}
