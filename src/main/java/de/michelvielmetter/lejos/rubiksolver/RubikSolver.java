package de.michelvielmetter.lejos.rubiksolver;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;

/**
 * ╔================================ RubikSolver ====================================
 * ║
 * ║ Create Date: 17.06.2015
 * ║
 * ║ Author(s): Michel Vielmetter <contact@michelvielmetter.de>
 * ║ Copyright: Michel Vielmetter 2015
 * ║ Licence:   MIT License
 * ║
 * ╠================================ RubikSolver ====================================
 * ║
 * ║ Package:   de.michelvielmetter.lejos.rubiksolver
 * ║
 * ╚================================ RubikSolver ====================================
 */
public class RubikSolver extends Thread
{
    private Brick brick;

    private Arm arm;
    private ColorArm colorArm;
    private Table table;
    private RubikCube cube;

    public RubikSolver()
    {
        brick = BrickFinder.getDefault();
        arm = new Arm(this);
        colorArm = new ColorArm(this);
        table = new Table(this);
        cube = new RubikCube(this);

        arm.start();
        colorArm.start();
        table.start();
    }

    public void run()
    {
        // TODO bind debugkey ALS ERSTES!!!!!!!

        // TODO Read Color

        // TODO Find Algorithm

        // TODO Solve
    }

    public Brick getBrick()
    {
        return brick;
    }

    public Table getTable()
    {
        return table;
    }

    public ColorArm getColorArm()
    {
        return colorArm;
    }

    public Arm getArm()
    {
        return arm;
    }
}
