//Contestant #1743
package com.skillsusa.rubikscube;

import com.skillsusa.rubikscube.solver.Solver;

import javax.swing.*;

public class Engine {

    private JFrame mainFrame;
    private Drawer drawer;
    private RubiksCube rubiksCube;
    private boolean isRunning;
    private Solver solver;
    private boolean solving;

    /**
     * Sets up the drawing functions of the programs and the Rubik's Cube object.
     */
    public Engine() {
        mainFrame = new JFrame("Rubik's Cube");
        drawer = new Drawer(this);
        mainFrame.add(drawer.getCanvas());
        drawer.getCanvas().addKeyListener(new RubiksKeyListener());
        mainFrame.pack();
        drawer.setUpGraphics();
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);

        rubiksCube = new RubiksCube();
        drawer.setRubiksCube(rubiksCube);
        solver = new Solver(rubiksCube);
        solver.randomize();
        drawer.getCanvas().addMouseListener(new RubiksMouseListener(rubiksCube, solver, this));
        isRunning = true;
        solving = false;
        run();
    }

    /**
     * starts solving the cube.
     */
    public void startSolving() {
        solving = true;
    }

    /**
     * stops solving the cube.
     */
    public void stopSolving() {
        solving = false;
    }

    /**
     * @return if the cube is currently being auto-solved
     */
    public boolean isSolving() {
        return solving;
    }

    /**
     * runs the program. Draws each frame and has an update rate of 30ms/update.
     */
    public void run() {
        while (isRunning) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (solving) {
                solver.makeMove();
            }
            drawer.draw();
            drawer.getCanvas().getBufferStrategy().show();
        }
    }

}
