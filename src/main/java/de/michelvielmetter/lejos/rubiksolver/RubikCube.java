package de.michelvielmetter.lejos.rubiksolver;

import de.michelvielmetter.lejos.util.LejosHelper;

import javax.activity.InvalidActivityException;

/**
 * ╔================================ RubikCube ====================================
 * ║
 * ║ Create Date: 23.06.2015
 * ║
 * ║ Author(s): Michel Vielmetter <contact@michelvielmetter.de>
 * ║ Copyright: Michel Vielmetter 2015
 * ║ Licence:   MIT License
 * ║
 * ╠================================ RubikCube ====================================
 * ║
 * ║ Package:   de.michelvielmetter.lejos.rubiksolver
 * ║
 * ╚================================ RubikCube ====================================
 */
public class RubikCube
{
    private RubikSide[] sides = new RubikSide[6];

    private RubikSolver solver;

    public RubikCube(RubikSolver solver)
    {
        super();
        this.solver = solver;

        for (int i = 0; i < 6; i++) {
            sides[i] = new RubikSide(i, this);
        }
    }

    public RubikSide getSide(int side)
    {
        return sides[side];
    }

    public RubikSide setSide(RubikSide side, int newSide)
    {
        RubikSide current = getSide(newSide);
        side.setCurrentSide(newSide);
        sides[newSide] = side;
        return current;
    }

    public RubikSolver getSolver()
    {
        return solver;
    }

    // MOVEMENTS
    public boolean x()
    {
        return x(true);
    }

    public boolean x(int times)
    {
        return x(times, true);
    }

    public boolean x(int times, boolean toZero)
    {
        for (int i = 0; i < times - 1; i++) {
            if (!x(false)) {
                return false;
            }
        }

        return x(toZero);
    }

    public boolean x(boolean toZero)
    {
        getSide(RubikSide.LEFT).counterClockwise();
        getSide(RubikSide.RIGHT).clockwise();
        RubikSide side = getSide(RubikSide.TOP);
        sides[RubikSide.TOP] = null;

        int i = RubikSide.TOP;
        while (side != null) {
            if (side.getCurrentSide() == RubikSide.FRONT || side.getCurrentSide() == RubikSide.DOWN) {
                side.clockwise(2);
            }
            if (i == 3) {
                side = setSide(side, RubikSide.TOP);
            } else {
                side = setSide(side, ++i);
            }
        }

        try {
            solver.getArm().rotateCube(toZero);
        } catch (InvalidActivityException e) {
            LejosHelper.getDisplay().clear();
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean clockwise(int times)
    {
        for (int i = 0; i < times; i++) {
            if (!clockwise()) {
                return false;
            }
        }

        return true;
    }

    public boolean clockwise()
    {
        getSide(RubikSide.TOP).counterClockwise();
        getSide(RubikSide.DOWN).clockwise();
        RubikSide side = getSide(RubikSide.BACK);
        sides[RubikSide.BACK] = null;

        while (side != null) {
            switch (side.getCurrentSide()) {
                case RubikSide.BACK:
                    side = setSide(side, RubikSide.RIGHT);
                    break;
                case RubikSide.RIGHT:
                    side = setSide(side, RubikSide.FRONT);
                    break;
                case RubikSide.FRONT:
                    side = setSide(side, RubikSide.LEFT);
                    break;
                case RubikSide.LEFT:
                    side = setSide(side, RubikSide.BACK);
                    break;
            }
        }

        try {
            getSolver().getTable().goToPos(Table.POS_EDGE);
        } catch (InvalidActivityException e) {
            return false;
        }

        return true;
    }

    public boolean counterClockwise(int times)
    {
        for (int i = 0; i < times; i++) {
            if (!counterClockwise()) {
                return false;
            }
        }

        return true;
    }

    public boolean counterClockwise()
    {
        getSide(RubikSide.TOP).clockwise();
        getSide(RubikSide.DOWN).counterClockwise();
        RubikSide side = getSide(RubikSide.BACK);
        sides[RubikSide.BACK] = null;

        while (side != null) {
            switch (side.getCurrentSide()) {
                case RubikSide.BACK:
                    side = setSide(side, RubikSide.LEFT);
                    break;
                case RubikSide.LEFT:
                    side = setSide(side, RubikSide.FRONT);
                    break;
                case RubikSide.FRONT:
                    side = setSide(side, RubikSide.RIGHT);
                    break;
                case RubikSide.RIGHT:
                    side = setSide(side, RubikSide.BACK);
                    break;
            }
        }

        try {
            getSolver().getTable().goToPos(-Table.POS_EDGE);
        } catch (InvalidActivityException e) {
            return false;
        }

        return true;
    }

    public boolean readSides()
    {
        RubikSide[] sides = this.sides.clone();

        for (RubikSide side : sides) {
            side.getColors();
        }

        return true;
    }
}
