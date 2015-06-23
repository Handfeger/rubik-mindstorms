package de.michelvielmetter.lejos.rubiksolver;

import de.michelvielmetter.lejos.util.Display;
import de.michelvielmetter.lejos.util.LejosHelper;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;

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
    private Display display;

    public final boolean debug = true;

    public RubikSolver()
    {
        brick = BrickFinder.getDefault();
        arm = new Arm(this);
        colorArm = new ColorArm(this);
        table = new Table(this);
        cube = new RubikCube(this);
        display = LejosHelper.getDisplay();

        arm.start();
        colorArm.start();
        table.start();

    }

    public void run()
    {
        // Debug
        if (debug) {
            LejosHelper.getKeyBinder().addKey("Enter", "Debug", new KeyListener()
            {
                @Override
                public void keyPressed(Key k)
                {
                    printSide(cube.getSide(RubikSide.TOP));
                }

                @Override
                public void keyReleased(Key k)
                {

                }
            });
        }

        // TODO Read Color

        // TODO Find Algorithm

        // TODO Solve

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            // nix
        }
    }

    public void printSide(RubikSide side)
    {
        side.print(display);
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
