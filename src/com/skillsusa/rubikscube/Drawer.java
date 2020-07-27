//Contestant #1743
package com.skillsusa.rubikscube;

import java.awt.*;

public class Drawer {

    private Canvas canvas;
    private Graphics2D graphics2D;
    private RubiksCube rubiksCube;
    private Engine engine;

    /**
     * Default construct for the class. Just sets up the canvas.
     */
    public Drawer(Engine engine) {
        canvas  = new Canvas();
        canvas.setSize(600, 600);
        canvas.setBackground(Color.BLACK);
        this.engine = engine;
    }

    /**
     * Sets up the canvas bufferStrategy and graphics2D for drawing.
     */
    public void setUpGraphics() {
        canvas.createBufferStrategy(2);
        graphics2D = (Graphics2D) canvas.getBufferStrategy().getDrawGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    /**
     *  grabs the rubiks cube for local use
     * @param rubiksCube yes
     */
    public void setRubiksCube(RubiksCube rubiksCube) {
        this.rubiksCube = rubiksCube;
    }

    /**
     * @return the canvas in which the program is drawn
     */
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * Draws the cube and the text 'buttons' to move the cube. Also draws the solve button.
     */
    public void draw() {
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(0,0,600,600);

        drawCube();

        graphics2D.setColor(Color.WHITE);
        graphics2D.getFont().deriveFont(Font.BOLD, 24);
        graphics2D.drawString("<<",20,260);
        graphics2D.drawString("<<",20,300);
        graphics2D.drawString("<<",20,340);
        graphics2D.drawString(">>",560,300);
        graphics2D.drawString(">>",560,340);
        graphics2D.drawString(">>",560,260);
        graphics2D.drawString("^^", 190, 100);
        graphics2D.drawString("^^", 230, 100);
        graphics2D.drawString("^^", 270, 100);
        graphics2D.drawString("vv", 190, 500);
        graphics2D.drawString("vv", 230, 500);
        graphics2D.drawString("vv", 270, 500);
        graphics2D.drawString("^^", 310, 220);
        graphics2D.drawString("^^", 350, 220);
        graphics2D.drawString("^^", 390, 220);
        graphics2D.drawString("vv", 310, 380);
        graphics2D.drawString("vv", 350, 380);
        graphics2D.drawString("vv", 390, 380);

        if (engine.isSolving()) {
            graphics2D.setColor(Color.GREEN);
            graphics2D.fillRect(200,540, 100, 40);
            graphics2D.setColor(Color.BLACK);
            graphics2D.drawString("SOLVING",215,560);
        } else {
            graphics2D.setColor(Color.RED);
            graphics2D.fillRect(200,540, 100, 40);
            graphics2D.setColor(Color.WHITE);
            graphics2D.drawString("SOLVE",220,560);
        }

        canvas.getBufferStrategy().show();
    }


    /**
     * draws each color block of the cube to the canvas
     */
    public void drawCube() {
        int scale = 4;
        for (int i = 0; i < 6; i++) {
            RubiksSide currentSide = rubiksCube.getRubiksSide(i+1);
            if (currentSide.getSideNumber() == 5) {
                for (int j = 0; j < 9; j++) {
                    RubiksPiece piece = currentSide.getPiece(j);
                    graphics2D.setColor(piece.getPieceColor());
                    int x = (60*scale) + (piece.getxPos()*(10*scale))-(10*scale)-60;
                    int y = (20*scale) + (piece.getyPos()*(10*scale));
                    graphics2D.fillRect(x,y,(10*scale),(10*scale));
                    graphics2D.setColor(Color.BLACK);
                    graphics2D.drawRect(x,y,(10*scale),(10*scale));
                }
            } else if (currentSide.getSideNumber() == 6) {
                for (int j = 0; j < 9; j++) {
                    RubiksPiece piece = currentSide.getPiece(j);
                    graphics2D.setColor(piece.getPieceColor());
                    int x = (60*scale) + (piece.getxPos()*(10*scale))-(10*scale)-60;
                    int y = (80*scale) + (piece.getyPos()*(10*scale));
                    graphics2D.fillRect(x,y,(10*scale),(10*scale));
                    graphics2D.setColor(Color.BLACK);
                    graphics2D.drawRect(x,y,(10*scale),(10*scale));
                }
            } else {
                for (int j = 0; j < 9; j++) {
                    RubiksPiece piece = currentSide.getPiece(j);
                    graphics2D.setColor(piece.getPieceColor());
                    int x = (currentSide.getSideNumber() * 30*scale) + (piece.getxPos()*(10*scale))-(10*scale)-60;
                    int y = (50*scale) + (piece.getyPos()*(10*scale));
                    graphics2D.fillRect(x,y,(10*scale),(10*scale));
                    graphics2D.setColor(Color.BLACK);
                    graphics2D.drawRect(x,y,(10*scale),(10*scale));
                }
            }
        }
    }

}
