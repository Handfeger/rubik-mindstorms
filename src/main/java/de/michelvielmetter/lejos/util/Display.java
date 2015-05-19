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

    private boolean autoRefresh = false;

    public Display(Brick brick)
    {
        this.lcd = brick.getGraphicsLCD();
    }


    public Display(GraphicsLCD display)
    {
        this.lcd = display;
    }


    public void draw(String text, int line, int xPos)
    {
        lcd.drawString(text, xPos, line, GraphicsLCD.LEFT);

        refresh(true);
    }

    public void draw(String text, int line)
    {
        lcd.drawString(text, 0, line, GraphicsLCD.HCENTER);

        refresh(true);
    }

    public void draw(String text)
    {
        lcd.drawString(text, 1, 5, GraphicsLCD.HCENTER | GraphicsLCD.VCENTER);

        refresh(true);
    }

    public void clear()
    {
        lcd.clear();
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
}
