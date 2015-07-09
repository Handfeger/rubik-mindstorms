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
        return zero;
    }

    protected void setZero(int zero)
    {
        this.zero = zero;
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

    public final void rotate(int degrees) throws InvalidActivityException {
        if (degrees == 0) {
            return;
        }
        if (isLocked()) {
            throw new InvalidActivityException("Trying to move " + this.getClass() + " in locked state");
        }

        if (zero == 0) {
            hardLockDependants();
        }
        lockDependants();

        motor.rotate(degrees);
        updateZero(degrees);

        releaseDependants();
        if (zero == 0) {
            hardReleaseDependants();
        }
    }

    public void goToZero() throws InvalidActivityException
    {
        rotate(-zero);
    }

    protected abstract void hardLockDependants();

    protected abstract void hardReleaseDependants();

    protected abstract void lockDependants();

    protected abstract void releaseDependants();

    protected void goToPos(int degrees) throws InvalidActivityException
    {
        rotate(degrees - getZero());
    }

    protected void setSpeed(int speed)
    {
        motor.setSpeed(speed);
    }
    protected void setSpeed(){
        motor.setSpeed();
    }

    protected void updateZero(int degrees)
    {
        zero += degrees;
    }

    public void findZero()
    {

    }
}
