package de.michelvielmetter.lejos.rubiksolver;

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
        return null;
    }

    public RubikSolver getSolver()
    {
        return solver;
    }
    
    // MOVEMENTS
    public boolean y()
    {
        getSide(RubikSide.LEFT).clockwise();
        getSide(RubikSide.RIGHT).counterClockwise();
        RubikSide side = getSide(RubikSide.TOP);
        int i = RubikSide.TOP;
        while (side != null) {
            if (side.getCurrentSide() == RubikSide.FRONT || side.getCurrentSide() == RubikSide.DOWN) {
                side.clockwise(2);
            }
            side = setSide(side, ++i);
        }
        return true;
    }

    public boolean y(int times)
    {
        for (int i = 0; i < times; i++) {
            if (!y()) {
                return false;
            }
        }
        
        return true;
    }

    public boolean clockwise()
    {
        // TODO rotate clockwise
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

    public boolean counterClockwise()
    {
        // TODO rotate counterClockwise
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
}
