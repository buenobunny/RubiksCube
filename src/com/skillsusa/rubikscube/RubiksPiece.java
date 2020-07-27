//Contestant #1743
package com.skillsusa.rubikscube;

import java.awt.*;

public class RubiksPiece {

    private Color pieceColor;
    private int xPos;
    private int yPos;

    /**
     * a simple container for the information of a piece on its respective side
     * @param pieceColor the color of the piece. can be arbitrary
     * @param xPos the x position of it goes left to right 1-2-3
     * @param yPos the y position goes top to bottom 1-2-3
     */
    public RubiksPiece(Color pieceColor, int xPos, int yPos) {
        this.pieceColor = pieceColor;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Color getPieceColor() {
        return pieceColor;
    }

    public void setPieceColor(Color pieceColor) {
        this.pieceColor = pieceColor;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
}
