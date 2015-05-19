package de.michelvielmetter.lejos.util;

import lejos.hardware.Brick;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private LinkedList<ActionListener> listeners;

    public KeyBinder(Brick brick)
    {
        brick = brick;
        display = null;
        print = false;

        setup();
    }

    public KeyBinder(Brick brick, Display display, boolean print)
    {
        brick = brick;
        display = display;
        print = print;

        setup();
    }

    private void setup()
    {
        listeners = new LinkedList<>();
        addCancelKey();
    }

    public void addCancelKey()
    {
        add("Escape", "Quit", new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO
            }
        });
    }

    public void add(String buttonType, String description, ActionListener e)
    {
        listeners.add(e);

        printButton(buttonType, description);
    }

    private void printButton(String type, String name)
    {
        if (display == null || !print) {
            return;
        }
        int line = 1;
        // TODO make buttons printable
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
        display.draw(type + ":", line, 1);
        display.draw(name, line, 7);
        //
    }
}
