package de.michelvielmetter.lejos.rubiksolver;

import de.michelvielmetter.lejos.util.LejosHelper;

/**
 * ╔================================ ColorArm ====================================
 * ║
 * ║ Create Date: 17.06.2015
 * ║
 * ║ Author(s): Michel Vielmetter <contact@michelvielmetter.de>
 * ║ Copyright: Michel Vielmetter 2015
 * ║ Licence:   MIT License
 * ║
 * ╠================================ ColorArm ====================================
 * ║
 * ║ Package:   de.michelvielmetter.lejos.rubiksolver
 * ║
 * ╚================================ ColorArm ====================================
 */
public class ColorArm extends MotorPart
{

    public ColorArm(RubikSolver rubik)
    {
        super(rubik, LejosHelper.getMediumMotor("C", rubik.getBrick()));
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
}
