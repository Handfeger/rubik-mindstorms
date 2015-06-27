package de.michelvielmetter.lejos.rubiksolver;

import de.michelvielmetter.lejos.util.LejosHelper;

import javax.activity.InvalidActivityException;

/**
 * ╔================================ Arm ====================================
 * ║
 * ║ Create Date: 17.06.2015
 * ║
 * ║ Author(s): Michel Vielmetter <contact@michelvielmetter.de>
 * ║ Copyright: Michel Vielmetter 2015
 * ║ Licence:   MIT License
 * ║
 * ╠================================ Arm ====================================
 * ║
 * ║ Package:   de.michelvielmetter.lejos.rubiksolver
 * ║
 * ╚================================ Arm ====================================
 */
public class Arm extends MotorPart
{
    public static final int POS1 = 110;
    public static final int POS2 = 200;

    public Arm(RubikSolver rubik)
    {
        super(rubik, LejosHelper.getLargeMotor("A", rubik.getBrick()));
        // TODO set speed
    }

    @Override
    protected void hardLockDependants()
    {
        this.getRubik().getColorArm().lock();
    }

    @Override
    protected void hardReleaseDependants()
    {
        this.getRubik().getColorArm().release();
    }

    @Override
    protected void lockDependants()
    {
        this.getRubik().getColorArm().lock();
        this.getRubik().getTable().lock();
    }

    @Override
    protected void releaseDependants()
    {
        this.getRubik().getColorArm().release();
        this.getRubik().getTable().release();
    }

    public void rotateCube(boolean toZero) throws InvalidActivityException
    {
        if (getZero() != 0 && getZero() != Arm.POS1) {
            goToZero();
        }
        goToPos(Arm.POS2);
        if (toZero) {
            goToZero();
        } else {
            goToPos(Arm.POS1);
        }
    }

    public void fixCubPos() throws InvalidActivityException
    {
        goToZero();
        goToPos(Arm.POS1);
        goToZero();
    }
}
