package de.michelvielmetter.lejos.util;

import lejos.hardware.Brick;
import lejos.hardware.lcd.GraphicsLCD;

/**
 * ╔================================ Display ====================================
 * ║
 * ║ Create Date: 30.04.2015
 * ║
 * ║ Author(s): Michel Vielmetter <contact@michelvielmetter.de>
 * ║ Copyright: Michel Vielmetter 2015
 * ║ Licence:   MIT License
 * ║
 * ╠================================ Display ====================================
 * ║
 * ║ Package:   de.michelvielmetter.lejos.util
 * ║
 * ╚================================ Display ====================================
 */
public class Display
{
    private GraphicsLCD lcd;

    public void setAutoRefresh(boolean autoRefresh)
    {
        this.autoRefresh = autoRefresh;
    }

    private boolean autoRefresh = true;

    public Display(Brick brick)
    {
        this.lcd = brick.getGraphicsLCD();
    }


    public Display(GraphicsLCD display)
    {
        this.lcd = display;
    }


    public void drawString(String text, int line, int xPos)
    {
        lcd.drawString(text, xPos, translateLine(line), GraphicsLCD.LEFT);

        refresh(true);
    }

    public void drawString(String text, int line)
    {
        drawString(text, line, 1);

        refresh(true);
    }

    public void drawString(String text)
    {
        drawString(text, 1, 1);

        refresh(true);
    }

    public void clear()
    {
        lcd.clear();

        refresh(true);
    }

    public boolean refresh()
    {
        return refresh(false);
    }
    public boolean refresh(boolean isAutoRefresh)
    {
        if (isAutoRefresh && !autoRefresh) {
            return false;
        }

        lcd.refresh();

        return true;
    }

    private int translateLine(int line)
    {
        return (line - 1) * 5 + 1;
    }
}
