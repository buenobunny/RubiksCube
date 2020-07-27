//Contestant #1743
package com.skillsusa.rubikscube;

import com.skillsusa.rubikscube.solver.Solver;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class RubiksMouseListener implements MouseInputListener {

    private RubiksCube rubiksCube;
    private Solver solver;
    private Engine engine;

    /**
     * A default constructor that sets up the class.
     * @param rubiksCube saves cube in the class for later use.
     */
    public RubiksMouseListener(RubiksCube rubiksCube, Solver solver, Engine engine) {
        this.rubiksCube = rubiksCube;
        this.solver = solver;
        this.engine = engine;
    }

    /**
     * Checks for when the mouse is clicked and moves the cube accordingly.
     * @param e a MouesEvent that gives the proper details of the mouse click.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        System.out.println(e.getX() + ", " + e.getY());
        if (mouseX > 0 && mouseX < 60 && mouseY > 240 && mouseY < 280) rubiksCube.moveLeft(1);
        else if (mouseX > 0 && mouseX < 60 && mouseY > 280 && mouseY < 320) rubiksCube.moveLeft(2);
        else if (mouseX > 0 && mouseX < 60 && mouseY > 320 && mouseY < 360) rubiksCube.moveLeft(3);
        else if (mouseX > 540 && mouseX < 600 && mouseY > 240 && mouseY < 280) rubiksCube.moveRight(1);
        else if (mouseX > 540 && mouseX < 600 && mouseY > 280 && mouseY < 320) rubiksCube.moveRight(2);
        else if (mouseX > 540 && mouseX < 600 && mouseY > 320 && mouseY < 360) rubiksCube.moveRight(3);
        else if (mouseX > 180 && mouseX < 220 && mouseY > 80 & mouseY < 120)
            rubiksCube.movePieces(true, true, false, 0, 1);
        else if (mouseX > 220 && mouseX < 260 && mouseY > 80 & mouseY < 120)
            rubiksCube.movePieces(true, true, false, 0, 2);
        else if (mouseX > 260 && mouseX < 300 && mouseY > 80 & mouseY < 120)
            rubiksCube.movePieces(true, true, false, 0, 3);
        else if (mouseX > 180 && mouseX < 220 && mouseY > 480 & mouseY < 520)
            rubiksCube.movePieces(true, false, false, 0, 1);
        else if (mouseX > 220 && mouseX < 260 && mouseY > 480 & mouseY < 520)
            rubiksCube.movePieces(true, false, false, 0, 2);
        else if (mouseX > 260 && mouseX < 300 && mouseY > 480 & mouseY < 520)
            rubiksCube.movePieces(true, false, false, 0, 3);
        else if (mouseX > 300 && mouseX < 340 && mouseY > 200 & mouseY < 240)
            rubiksCube.movePieces(false, true, true, 0, 1);
        else if (mouseX > 340 && mouseX < 380 && mouseY > 200 & mouseY < 240)
            rubiksCube.movePieces(false, true, true, 0, 2);
        else if (mouseX > 380 && mouseX < 420 && mouseY > 200 & mouseY < 240)
            rubiksCube.movePieces(false, true, true, 0, 3);
        else if (mouseX > 300 && mouseX < 340 && mouseY > 360 & mouseY < 400)
            rubiksCube.movePieces(false, false, true, 0, 1);
        else if (mouseX > 340 && mouseX < 380 && mouseY > 360 & mouseY < 400)
            rubiksCube.movePieces(false, false, true, 0, 2);
        else if (mouseX > 380 && mouseX < 420 && mouseY > 360 & mouseY < 400)
            rubiksCube.movePieces(false, false, true, 0, 3);

        if (mouseX > 200 && mouseX < 300 && mouseY > 540 && mouseY < 580 && !engine.isSolving()) engine.startSolving();
        else if (mouseX > 200 && mouseX < 300 && mouseY > 540 && mouseY < 580 && engine.isSolving())
            engine.stopSolving();
    }

    //ALL UNUSED IMPLEMENTED METHODS
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
