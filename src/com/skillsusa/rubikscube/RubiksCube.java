//Contestant #1743
package com.skillsusa.rubikscube;

import java.awt.*;
import java.util.ArrayList;

public class RubiksCube {

    private ArrayList<RubiksSide> rubiksSides;

    /**
     * instantiates the ArrayList with proper colors and format.
     */
    public RubiksCube() {

        rubiksSides = new ArrayList<>();
        rubiksSides.add(new RubiksSide(1, Color.GREEN));
        rubiksSides.add(new RubiksSide(2, Color.WHITE));
        rubiksSides.add(new RubiksSide(3, Color.BLUE));
        rubiksSides.add(new RubiksSide(4, Color.YELLOW));
        rubiksSides.add(new RubiksSide(5, Color.magenta));
        rubiksSides.add(new RubiksSide(6, Color.RED));

    }

    /**
     * @param sideNumber not the index, but the actual side number as defined
     * @return the appropriate side for the corresponding sideNumber
     */
    public RubiksSide getRubiksSide(int sideNumber) {
        for (int i = 0; i < rubiksSides.size(); i++) {
            if (rubiksSides.get(i).getSideNumber() == sideNumber) {
                return rubiksSides.get(i);
            }
        }
        return null;
    }

    /**
     * returns the sides for use
     * @return the list of sides
     */
    public ArrayList<RubiksSide> getRubiksSides() {
        return rubiksSides;
    }

    /**
     * handles movement of pieces when clicked. This is really heavy, but I didn't see another way to do it.
     * @param horizontal determines whether or not the movement is horizontal.
     * @param direction determines whether the movement is left or up for true and right or down for false
     * @param third determines whether or not the movement is in the third dimension of the cube.
     * @param row gives the row of the movement in question
     * @param col gives the column of the movement in question
     */
    public void movePieces(boolean horizontal, boolean direction, boolean third, int row, int col) {
        //direction - true for left, up. false for right, down. third is the third dimension: true for up, false for down
        if (horizontal) {
            if (direction) {
                //moves the column in question up
                RubiksPiece[] tempPieces = {rubiksSides.get(5).getPiece(col, 1), rubiksSides.get(5).getPiece(col, 2), rubiksSides.get(5).getPiece(col, 3)};
                for (int j = 1; j < 4; j++) {
                    rubiksSides.get(5).setPiece(col, j, rubiksSides.get(3).getPiece(col, j));
                }
                for (int j = 1; j < 4; j++) {
                    rubiksSides.get(3).setPiece(col, j, rubiksSides.get(4).getPiece(col, j));
                }
                for (int j = 1; j < 4; j++) {
                    rubiksSides.get(4).setPiece(col, j, rubiksSides.get(1).getPiece(col, j));
                }
                for (int j = 1; j < 4; j++) {
                    rubiksSides.get(1).setPiece(col, j, tempPieces[j-1]);
                }
            } else {
                //moves the row in question down
                RubiksPiece[] tempPieces = {rubiksSides.get(4).getPiece(col, 1), rubiksSides.get(4).getPiece(col, 2), rubiksSides.get(4).getPiece(col, 3)};
                for (int j = 1; j < 4; j++) {
                    rubiksSides.get(4).setPiece(col, j, rubiksSides.get(3).getPiece(col, j));
                }
                for (int j = 1; j < 4; j++) {
                    rubiksSides.get(3).setPiece(col, j, rubiksSides.get(5).getPiece(col, j));
                }
                for (int j = 1; j < 4; j++) {
                    rubiksSides.get(5).setPiece(col, j, rubiksSides.get(1).getPiece(col, j));
                }
                for (int j = 1; j < 4; j++) {
                    rubiksSides.get(1).setPiece(col, j, tempPieces[j-1]);
                }
            }
        } else if (third) {
            if (direction) {
                /*
                YEAH I KNOW THIS IS TERRIBLE PRACTICE BUT I COULDN'T FIGURE IT OUT OTHERWISE :(
                also I wanted to start solving
                 */
                RubiksPiece[] tempPieces = {rubiksSides.get(2).getPiece(col, 1), rubiksSides.get(2).getPiece(col, 2), rubiksSides.get(2).getPiece(col, 3)};
                rubiksSides.get(2).setPiece(col, 1, new RubiksPiece( rubiksSides.get(5).getPiece(3, col).getPieceColor(), col, 1));
                rubiksSides.get(2).setPiece(col, 2, new RubiksPiece( rubiksSides.get(5).getPiece(2, col).getPieceColor(), col, 2));
                rubiksSides.get(2).setPiece(col, 3, new RubiksPiece( rubiksSides.get(5).getPiece(1, col).getPieceColor(), col, 3));
                rubiksSides.get(5).setPiece(3, col, new RubiksPiece( rubiksSides.get(0).getPiece(4-col, 3).getPieceColor(), 3, col));
                rubiksSides.get(5).setPiece(2, col, new RubiksPiece( rubiksSides.get(0).getPiece(4-col, 2).getPieceColor(), 2, col));
                rubiksSides.get(5).setPiece(1, col, new RubiksPiece( rubiksSides.get(0).getPiece(4-col, 1).getPieceColor(), 1, col));
                rubiksSides.get(0).setPiece(4-col, 1, new RubiksPiece( rubiksSides.get(4).getPiece(3, 4-col).getPieceColor(), 4-col, 1));
                rubiksSides.get(0).setPiece(4-col, 2, new RubiksPiece( rubiksSides.get(4).getPiece(2, 4-col).getPieceColor(), 4-col, 2));
                rubiksSides.get(0).setPiece(4-col, 3, new RubiksPiece( rubiksSides.get(4).getPiece(1, 4-col).getPieceColor(), 4-col, 3));
                rubiksSides.get(4).setPiece(1, 4-col, new RubiksPiece(tempPieces[0].getPieceColor(), 1, 4-col));
                rubiksSides.get(4).setPiece(2, 4-col, new RubiksPiece(tempPieces[1].getPieceColor(), 2, 4-col));
                rubiksSides.get(4).setPiece(3, 4-col, new RubiksPiece(tempPieces[2].getPieceColor(), 3, 4-col));
                if (col==1) {
                    RubiksSide newSide = new RubiksSide(2, Color.WHITE);
                    for (int x = 1; x < 4; x++) {
                        for (int y = 1; y < 4; y++) {
                            newSide.getPiece(x, y).setPieceColor(rubiksSides.get(1).getPiece(4 - y, x).getPieceColor());
                        }
                    }
                    rubiksSides.get(1).replacePieces(newSide.getAllPieces());
                } else if (col == 3) {
                    RubiksSide newSide = new RubiksSide(4, Color.WHITE);
                    for (int x = 1; x < 4; x++) {
                        for (int y = 1; y < 4; y++) {
                            newSide.getPiece(x, y).setPieceColor(rubiksSides.get(3).getPiece(4 - y, x).getPieceColor());
                        }
                    }
                    rubiksSides.get(3).replacePieces(newSide.getAllPieces());
                }
            } else {
                /*
                This took a while.
                 */
                RubiksPiece[] tempPieces = {rubiksSides.get(2).getPiece(col, 1), rubiksSides.get(2).getPiece(col, 2), rubiksSides.get(2).getPiece(col, 3)};
                rubiksSides.get(2).setPiece(col, 3, new RubiksPiece(rubiksSides.get(4).getPiece(3, 4-col).getPieceColor(), col, 3));
                rubiksSides.get(2).setPiece(col, 2, new RubiksPiece(rubiksSides.get(4).getPiece(2, 4-col).getPieceColor(), col, 2));
                rubiksSides.get(2).setPiece(col, 1, new RubiksPiece(rubiksSides.get(4).getPiece(1, 4-col).getPieceColor(), col, 1));
                rubiksSides.get(4).setPiece(3, 4-col, new RubiksPiece(rubiksSides.get(0).getPiece(4-col, 1).getPieceColor(), 3, 4-col));
                rubiksSides.get(4).setPiece(2, 4-col, new RubiksPiece(rubiksSides.get(0).getPiece(4-col, 2).getPieceColor(), 2, 4-col));
                rubiksSides.get(4).setPiece(1, 4-col, new RubiksPiece(rubiksSides.get(0).getPiece(4-col, 3).getPieceColor(), 1, 4-col));
                rubiksSides.get(0).setPiece(4-col, 1, new RubiksPiece(rubiksSides.get(5).getPiece(1, col).getPieceColor(), 4-col, 1));
                rubiksSides.get(0).setPiece(4-col, 2, new RubiksPiece(rubiksSides.get(5).getPiece(2, col).getPieceColor(), 4-col, 2));
                rubiksSides.get(0).setPiece(4-col, 3, new RubiksPiece(rubiksSides.get(5).getPiece(3, col).getPieceColor(), 4-col, 3));
                rubiksSides.get(5).setPiece(1, col, new RubiksPiece(tempPieces[2].getPieceColor(), 1, col));
                rubiksSides.get(5).setPiece(2, col, new RubiksPiece(tempPieces[1].getPieceColor(), 2, col));
                rubiksSides.get(5).setPiece(3, col, new RubiksPiece(tempPieces[0].getPieceColor(), 3, col));
                if (col==1) {
                    RubiksSide newSide = new RubiksSide(2, Color.WHITE);
                    for (int x = 1; x < 4; x++) {
                        for (int y = 1; y < 4; y++) {
                            newSide.getPiece(x, y).setPieceColor(rubiksSides.get(1).getPiece(y, 4 - x).getPieceColor());
                        }
                    }
                    rubiksSides.get(1).replacePieces(newSide.getAllPieces());
                } else if (col==3) {
                    RubiksSide newSide = new RubiksSide(4, Color.WHITE);
                    for (int x = 1; x < 4; x++) {
                        for (int y = 1; y < 4; y++) {
                            newSide.getPiece(x, y).setPieceColor(rubiksSides.get(3).getPiece(y, 4 - x).getPieceColor());
                        }
                    }
                    rubiksSides.get(3).replacePieces(newSide.getAllPieces());
                }
            }
        } else {
            //moves the row to the left
            if (direction) {
                RubiksPiece[] tempPieces = {rubiksSides.get(3).getPiece(1, row), rubiksSides.get(3).getPiece(2, row), rubiksSides.get(3).getPiece(3, row)};
                for (int i = 2; i >= 0; i--) { //side loop
                    for (int j = 1; j < 4; j++) { //piece loop
                        rubiksSides.get(i + 1).setPiece(j, row, rubiksSides.get(i).getPiece(j, row));
                    }
                }
                for (int i = 1; i < 4; i++) {
                    rubiksSides.get(0).setPiece(i, row, tempPieces[i-1]);
                }
            } else {
                //moves the row to the right
                RubiksPiece[] tempPieces = {rubiksSides.get(0).getPiece(1, row), rubiksSides.get(0).getPiece(2, row), rubiksSides.get(0).getPiece(3, row)};
                for (int i = 1; i < 4; i++) { //side loop
                    for (int j = 1; j < 4; j++) { //piece loop
                        rubiksSides.get(i - 1).setPiece(j, row, rubiksSides.get(i).getPiece(j, row));
                    }
                }
                for (int i = 1; i < 4; i++) {
                    rubiksSides.get(3).setPiece(i, row, tempPieces[i-1]);
                }
            }
        }
    }

    /**
     * the column to move up.
     * @param col the column in question.
     */
    public void moveUp(int col) {
        movePieces(true, true, false, 0, col);
    }

    /**
     * moves a column down on the graph.
     * @param col the column to move.
     */
    public void moveDown(int col) {
        movePieces(true, false, false, 0, col);
    }

    /**
     * moves the row in question right
     * @param row the row to move.
     */
    public void moveRight(int row) {
        movePieces(false, true, false, row, 0);
    }

    /**
     * moves the row in question left
     * @param row the row to move.
     */
    public void moveLeft(int row) {
        movePieces(false, false, false, row, 0);
    }

    /**
     * twists the cube up. it's not really up but on the 2d chart it is.
     * @param col the column to twist
     */
    public void twistUp(int col) {
        movePieces(false, true, true, 0, col);
    }

    /**
     * twists the cube down. it's not really down but on the 2d chart it is.
     * @param col the col on twist
     */
    public void twistDown(int col) {
        movePieces(false, false, true, 0, col);
    }

    /**
     * an easier way of tracking movement
     * @param move the move
     * @param row the row
     * @param col the column
     */
    public void move(String move, int row, int col) {
        switch(move.toLowerCase()) {
            case "up":
                movePieces(true, true, false, 0, col);
                break;
            case "down":
                movePieces(true, false, false, 0, col);
                break;
            case "right":
                movePieces(false, false, false, row, 0);
                break;
            case "left":
                movePieces(false, true, false, row, 0);
                break;
            case "twistup":
                movePieces(false, true, true, 0, col);
                break;
            case "twistdown":
                movePieces(false, false, true, 0, col);
                break;
        }
    }

}
