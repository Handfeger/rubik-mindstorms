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
    public static final int POS_HOLD = 100;
    public static final int POS_ROTATE = 198;

    public Arm(RubikSolver rubik)
    {
        super(rubik, LejosHelper.getLargeMotor("A", rubik.getBrick()));
        setSpeed(540);
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
        if (getZero() != 0 && getZero() != Arm.POS_HOLD) {
            goToZero();
        }
        goToPos(Arm.POS_ROTATE);
        if (toZero) {
            goToZero();
        } else {
            goToPos(Arm.POS_HOLD);
        }
    }

    public void fixCubPos() throws InvalidActivityException
    {
        goToZero();
        goToPos(Arm.POS_HOLD);
        goToZero();
    }

    @Override
    public void findZero()
    {
        setZero(8);
        try {
            goToZero();
        } catch (InvalidActivityException e) {
            e.printStackTrace();
        }
    }
}
