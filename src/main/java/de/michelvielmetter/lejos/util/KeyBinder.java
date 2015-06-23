package de.michelvielmetter.lejos.util;

import lejos.hardware.Brick;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;

import java.util.HashMap;
import java.util.Map;

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

    private HashMap<String, String> buttons = new HashMap<>();

    public void setInMenu(boolean inMenu)
    {
        this.inMenu = inMenu;
    }

    private boolean inMenu = false;

    public KeyBinder(Brick brick, Display display, boolean print)
    {
        this.brick = brick;
        this.display = display;
        this.print = print;

        setup();
    }

    private void setup()
    {
        addCancelKey();
    }

    public void addCancelKey()
    {
        addKey("Escape", "Quit", new KeyListener()
        {
            @Override
            public void keyPressed(Key key)
            {
            }

            @Override
            public void keyReleased(Key key)
            {
                if (!inMenu) {
                    System.exit(0);
                } else {
                    inMenu = false;
                    printAllButtons();
                }
            }
        });
    }

    public KeyBinder addKey(String buttonType, String description, KeyListener listener)
    {
        Key key = brick.getKey(buttonType);
        if (buttons.containsKey(buttonType)) {
            throw new IllegalArgumentException("Can't bind multiple Actions to Key " + buttonType);
        }
        key.addKeyListener(listener);

        buttons.put(buttonType, description);

        printButton(buttonType, description);

        return this;
    }

    private void printAllButtons()
    {
        display.setAutoRefresh(false);
        display.clear();
        for (Map.Entry<String, String> entry : buttons.entrySet()) {
            printButton(entry.getKey(), entry.getValue());
        }
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
        display.drawString(name, line, 7 * 10);
    }
}
