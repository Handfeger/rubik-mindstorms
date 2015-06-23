package de.michelvielmetter.lejos.util;

import lejos.hardware.Brick;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;

import java.util.LinkedList;

/**
 * ╔================================ KeyBinder ====================================
 * ║
 * ║ Create Date: 30.04.2015
 * ║
 * ║ Author(s): Michel Vielmetter <contact@michelvielmetter.de>
 * ║ Copyright: Michel Vielmetter 2015
 * ║ Licence:   MIT License
 * ║
 * ╠================================ KeyBinder ====================================
 * ║
 * ║ Package:   de.michelvielmetter.lejos.util
 * ║
 * ╚================================ KeyBinder ====================================
 */
public class KeyBinder
{
    private boolean print = false;
    private Brick brick;
    private Display display;

    private LinkedList<KeyListener> listeners;

    public KeyBinder(Brick brick)
    {
        this.brick = brick;
        display = null;
        print = false;

        setup();
    }

    public KeyBinder(Brick brick, Display display, boolean print)
    {
        this.brick = brick;
        this.display = display;
        this.print = print;

        setup();
    }

    private void setup()
    {
        listeners = new LinkedList<>();
        addCancelKey();
    }

    public void addCancelKey()
    {
        addKey("Escape", "Quit", new KeyListener()
        {
            @Override
            public void keyPressed(Key key)
            {
                System.exit(0);
            }

            @Override
            public void keyReleased(Key key)
            {

            }
        });
    }

    public void addKey(String buttonType, String description, KeyListener listener)
    {
        listeners.add(listener);

        brick.getKey(buttonType).addKeyListener(listener);

        printButton(buttonType, description);
    }

    private void printButton(String type, String name)
    {
        if (display == null || !print) {
            return;
        }
        int line = 1;
        switch (type) {
            case "Up":
                line = 3;
                break;
            case "Enter":
                line = 2;
                break;
            case "Down":
                line = 4;
                break;
            case "Right":
                line = 5;
                break;
            case "Left":
                line = 6;
                break;
            case "Escape":
                type = "Esc";
                line = 7;
                break;
            default:
                break;
        }

        display.drawString(type + ":", line, 1);
        display.drawString(name, line, 7*10);
        //
    }
}
