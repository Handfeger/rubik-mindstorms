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
    public Table(RubikSolver rubik)
    {
        super(rubik, LejosHelper.getLargeMotor("B", rubik.getBrick()));
    }

    @Override
    protected void hardLockDependants()
    {

    }

    @Override
    protected void hardReleaseDependants()
    {

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
}
