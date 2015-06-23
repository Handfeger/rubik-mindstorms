package de.michelvielmetter.lejos.rubiksolver;

import de.michelvielmetter.lejos.util.Display;
import de.michelvielmetter.lejos.util.LejosHelper;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;
import lejos.hardware.motor.Motor;

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

    public final boolean debug;

    public RubikSolver()
    {
        this(false);
    }

    public RubikSolver(boolean debug)
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

        this.debug = debug;
    }

    public void run()
    {
        // Debug
        if (debug) {
            LejosHelper.getKeyBinder().addKey("Enter", "Debug", new KeyListener()
            {
                private int currentSide = RubikSide.TOP;
                @Override
                public void keyPressed(Key k)
                {

                }

                @Override
                public void keyReleased(Key k)
                {
                    LejosHelper.getKeyBinder().setInMenu(true);
                    printSide(cube.getSide(currentSide++));
                    if (currentSide > 5) {
                        currentSide = RubikSide.TOP;
                    }
                }
            });
        }

        // TODO find zero positions

        LejosHelper.getKeyBinder().addKey("Up", "Read Color", new KeyListener()
        {
            @Override
            public void keyPressed(Key k)
            {

            }

            @Override
            public void keyReleased(Key k)
            {
                cube.readSides();
            }
        });

        // TODO Find Algorithm

        // TODO Solve

        try {
            Thread.sleep(60000);
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
