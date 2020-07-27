//Contestant #1743
package com.skillsusa.rubikscube.solver;

import com.skillsusa.rubikscube.RubiksCube;
import com.skillsusa.rubikscube.RubiksSide;

import java.awt.*;
import java.util.ArrayList;

public class Solver {

    private RubiksCube rubiksCube;
    private ArrayList<RubiksSide> sides;
    private String[] moves;
    private ArrayList<Move> nextMoves;
    private ArrayList<Move> previousMoves;

    /**
     * default constructor, sets up the moves lists.
     * @param rubiksCube rubik's cube to check and move
     */
    public Solver(RubiksCube rubiksCube) {
        this.rubiksCube = rubiksCube;
        sides = rubiksCube.getRubiksSides();
        moves = new String[]{"up", "down", "left", "right", "twistup", "twistdown"};
        nextMoves = new ArrayList<>();
        previousMoves = new ArrayList<>();
    }

    /**
     * decides what course of action to take depending on what circumstances there are. Terribly written imo
     */
    public void makeMove() {
        if(nextMoves.size() > 0) {
            rubiksCube.move(nextMoves.get(0).getMoveName(), nextMoves.get(0).getRow(), nextMoves.get(0).getCol());
            previousMoves.add(nextMoves.get(0));
            nextMoves.remove(0);
            return;
        }
        if (previousMoves.size()> 3 && previousMoves.get(previousMoves.size()-3).equals(previousMoves.get(previousMoves.size()-1))) {
            rubiksCube.twistDown(3);
            previousMoves.add(new Move("twistdown", 3, 0));
            return;
        }
        if (!whiteCross() && findEdgePiece(Color.WHITE) != null) {
            // This solves for the white crosses. it is very fallible. sometimes it goes into a loop and can't stop
            //It takes the data of where it is on the cube then moves it accordingly
            int[] edgePiece = findEdgePiece(Color.WHITE);
            if (edgePiece[0] == 3 && edgePiece[1] == 1) {
                rubiksCube.moveDown(1);
                previousMoves.add(new Move("down", 1, 0));
                nextMoves.add(new Move("down", 1, 0));
            } else if (edgePiece[0] == 4 && edgePiece[1] == 1) {
                rubiksCube.move("down", 0, 1);
                previousMoves.add(new Move("down", 1, 0));
            } else if (edgePiece[0] == 0 && edgePiece[2] == 1) {
                rubiksCube.move("right", 1, 0);
                previousMoves.add(new Move("right", 0, 1));
            } else if (edgePiece[0] == 2 && edgePiece[2] == 1) {
                rubiksCube.move("left", 1, 0);
                previousMoves.add(new Move("left", 0, 1));
            } else if (edgePiece[0] == 3 && edgePiece[2] == 1) {
                rubiksCube.move("right", 1, 0);
                previousMoves.add(new Move("right", 0, 1));
                nextMoves.add(new Move("right", 0, 1));
            } else if (edgePiece[0] == 4 && edgePiece[1] == 3) {
                rubiksCube.move("down", 0, 3);
                previousMoves.add(new Move("down", 0, 3));
            } else if (edgePiece[0] == 5 && edgePiece[1] == 3) {
                rubiksCube.move("up", 0, 3);
                previousMoves.add(new Move("up", 3, 0));
            } else if (edgePiece[0] == 0 && edgePiece[2] == 3) {
                rubiksCube.move("right", 3, 0);
                previousMoves.add(new Move("right", 0, 3));
            } else if (edgePiece[0] == 2 && edgePiece[2] == 3) {
                rubiksCube.move("left", 3, 0);
                previousMoves.add(new Move("left", 0, 03));
            } else if (edgePiece[0] == 3 && edgePiece[2] == 3) {
                rubiksCube.move("left", 3, 0);
                previousMoves.add(new Move("left", 0, 3));
                nextMoves.add(new Move("left", 0, 3));
            } else if (edgePiece[0] == 3 && edgePiece[1] == 3) {
                rubiksCube.move("up", 0, 3);
                previousMoves.add(new Move("up", 3, 0));
                nextMoves.add(new Move("up", 3, 0));
            } else if (edgePiece[0] == 5 && edgePiece[1] == 1) {
                rubiksCube.move("up", 0, 1);
                previousMoves.add(new Move("up", 1, 0));
            }
        } else if (!touchingCenter()) {
            //this basically gets all the center pieces into alignment.
            rubiksCube.move("twistdown", 0, 2);
            previousMoves.add(new Move("twistdown",2,0));
            System.out.println("twisting to center");
        } else if (!fullWhite()) {
            //I tried to do the next step here which is to do the corners with the sequence they had.
            rubiksCube.move("down", 0, 3);
            nextMoves.add(new Move("left", 0, 3));
            nextMoves.add(new Move("up", 3, 0));
            nextMoves.add(new Move("right", 0, 3));
        }
    }

    /**
     * checks if all the center pieces are touching their respective white-color piece.
     * @return bool if it is or isnt
     */
    public boolean touchingCenter() {
        if (sides.get(0).getPiece(2,2).getPieceColor().equals(
                sides.get(0).getPiece(3, 2).getPieceColor()
        )) {
            return true;
        }
        return false;
    }

    public boolean fullWhite() {
        for (int i = 0; i < 9; i++) {
            if (!sides.get(i).getPiece(i).getPieceColor().equals(Color.WHITE)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Finds the center piece of a certain color. Very useful
     * @param color
     * @return the index of the side where the color is
     */
    public int findCenterPiece(Color color) {
        for (int i = 0; i < sides.size(); i++) {
            if (sides.get(i).getPiece(2,2).getPieceColor().equals(Color.WHITE)) {
                return i;
            }
        }
        return 0;
    }

    /**
     * finds the first possible edge piece with the given color. also very useful
     * @param color
     * @return the int[] contains: side index, x, y
     */
    public int[] findEdgePiece(Color color) {
        int[] data = new int[3];
        for (int i = 0; i < sides.size(); i++) {
            if (sides.get(i).getPiece(1,2).getPieceColor().equals(Color.WHITE) && i != 1) {
                data[0] = i;
                data[1] = 1;
                data[2] = 2;
                return data;
            } else if (sides.get(i).getPiece(2,1).getPieceColor().equals(Color.WHITE) && i != 1) {
                data[0] = i;
                data[1] = 2;
                data[2] = 1;
                return data;
            } else if (sides.get(i).getPiece(3,2).getPieceColor().equals(Color.WHITE) && i != 1) {
                data[0] = i;
                data[1] = 3;
                data[2] = 2;
                return data;
            } else if (sides.get(i).getPiece(2,3).getPieceColor().equals(Color.WHITE) && i != 1) {
                data[0] = i;
                data[1] = 2;
                data[2] = 3;
                return data;
            }
        }
        return null;
    }

    public boolean whiteCross() {

        for (int i = 0; i < sides.size(); i++) {
            if (sides.get(i).getPiece(1,2).getPieceColor().equals(Color.WHITE) &&
                    sides.get(i).getPiece(2,1).getPieceColor().equals(Color.WHITE)&&
                    sides.get(i).getPiece(2,2).getPieceColor().equals(Color.WHITE)&&
                    sides.get(i).getPiece(2,3).getPieceColor().equals(Color.WHITE)&&
                    sides.get(i).getPiece(3,2).getPieceColor().equals(Color.WHITE)) {
                return true;
            }
        }

        return false;
    }

    public void randomize() {
        for (int i = 0; i < 50; i++) {
            int moveN = (int) (Math.random() * 6);
            int row;
            do {
                row = (int) (Math.random() * 3) + 1;
            } while (row == 2);
            int col;
            do {
                col = (int) (Math.random() * 3) + 1;
            } while (col == 2);
            rubiksCube.move(moves[moveN], row, col);
        }
    }

}
