package de.michelvielmetter.lejos.rubiksolver;


import de.michelvielmetter.lejos.util.helper.MotorHelper;
import lejos.hardware.Brick;

import javax.activity.InvalidActivityException;

/**
 * ╔================================ MotorPart ====================================
 * ║
 * ║ Create Date: 17.06.2015
 * ║
 * ║ Author(s): Michel Vielmetter <contact@michelvielmetter.de>
 * ║ Copyright: Michel Vielmetter 2015
 * ║ Licence:   MIT License
 * ║
 * ╠================================ MotorPart ====================================
 * ║
 * ║ Package:   de.michelvielmetter.lejos.rubiksolver
 * ║
 * ╚================================ MotorPart ====================================
 */
public abstract class MotorPart extends Thread
{
    private RubikSolver rubik;

    private int locked = 0;

    protected int getZero()
    {
        return -zero;
    }

    private int zero = 0;

    private MotorHelper motor;

    public MotorPart(RubikSolver rubik, MotorHelper motor)
    {
        this.rubik = rubik;
        this.motor = motor;
    }

    public RubikSolver getRubik()
    {
        return rubik;
    }

    public Brick getBrick()
    {
        return rubik.getBrick();
    }

    public void lock()
    {
        locked++;
    }

    public void release()
    {
        locked--;
    }

    public boolean isLocked()
    {
        return locked > 1;
    }

    public final void rotate(int degrees) throws InvalidActivityException
    {
        if (isLocked()) {
            throw new InvalidActivityException("Trying to move " + this.getClass() + " in locked state");
        }

        hardLockDependants();
        lockDependants();

        motor.rotate(degrees);
        zero -= degrees;

        releaseDependants();
        if (zero == 0) {
            hardReleaseDependants();
        }
    }

    public final void goToZero() throws InvalidActivityException
    {
        rotate(zero);
    }

    protected abstract void hardLockDependants();

    protected abstract void hardReleaseDependants();

    protected abstract void lockDependants();

    protected abstract void releaseDependants();

}
