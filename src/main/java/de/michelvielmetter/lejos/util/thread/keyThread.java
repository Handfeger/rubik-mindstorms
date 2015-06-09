package de.michelvielmetter.lejos.util.thread;

import de.michelvielmetter.lejos.util.KeyBinder;

import java.util.MissingFormatArgumentException;

/**
 * ╔================================ KeyThread ====================================
 * ║
 * ║ Create Date: 09.06.2015
 * ║
 * ║ Author(s): Michel Vielmetter <contact@michelvielmetter.de>
 * ║ Copyright: Michel Vielmetter 2015
 * ║ Licence:   MIT License
 * ║
 * ╠================================ KeyThread ====================================
 * ║
 * ║ Package:   de.michelvielmetter.lejos.util.thread
 * ║
 * ╚================================ KeyThread ====================================
 */
public class KeyThread extends Thread
{
    private final KeyBinder binder;

    private String mode;

    public KeyThread(KeyBinder binder)
    {
        this.binder = binder;
    }

    public void run() throws MissingFormatArgumentException
    {
        switch (mode) {
            case "escape":
                escapeKey();
            default:
                throw new MissingFormatArgumentException("Mode \"" + mode + "\" not found");
        }
    }

    private void escapeKey()
    {

    }
}
