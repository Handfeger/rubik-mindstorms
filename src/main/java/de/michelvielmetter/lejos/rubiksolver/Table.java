package de.michelvielmetter.lejos.rubiksolver;

import de.michelvielmetter.lejos.util.LejosHelper;

/**
 * ╔================================ Table ====================================
 * ║
 * ║ Create Date: 17.06.2015
 * ║
 * ║ Author(s): Michel Vielmetter <contact@michelvielmetter.de>
 * ║ Copyright: Michel Vielmetter 2015
 * ║ Licence:   MIT License
 * ║
 * ╠================================ Table ====================================
 * ║
 * ║ Package:   de.michelvielmetter.lejos.rubiksolver
 * ║
 * ╚================================ Table ====================================
 */
public class Table extends MotorPart
{
    public static final int POS_EDGE = 270;
    public static final int POS_CORNER = 135;
    public static final int POS_ROTATE = 317;

    public Table(RubikSolver rubik)
    {
        super(rubik, LejosHelper.getLargeMotor("B", rubik.getBrick()));
        setSpeed(350);
    }

    @Override
    protected void hardLockDependants()
    {
        this.getRubik().getArm().lock();
    }

    @Override
    protected void hardReleaseDependants()
    {
        this.getRubik().getArm().release();
    }

    @Override
    protected void lockDependants()
    {
        this.getRubik().getArm().lock();
    }

    @Override
    protected void releaseDependants()
    {
        this.getRubik().getArm().release();
    }

    @Override
    protected void updateZero(int degrees)
    {
        int current = getZero();
        current += degrees;
        current = current % 270;
        setZero(current);
    }
}
