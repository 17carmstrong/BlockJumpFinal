package main.Objects;

import main.CustomPanel;


public class Box 
{
    public double x = 0, y = 0, xVel = 0, yVel = 1, width = 1, height = 1;
    public boolean onSurface = false;
    public Box(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        yVel = Math.random();
    }
    public void run()
    {
        if(!onSurface)
        {
            y += yVel;
        }
    }
    public void setOnSurface()
    {
        onSurface = true;
        yVel = 0;
    }
}