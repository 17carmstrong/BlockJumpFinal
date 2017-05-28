package main.Math;

public class DoublePoint 
{
    public double x, y;
    //used in returning what type of collision player has with box
    //t = top
    //s = side
    //b = bottom
    public char surfaceType;
    public DoublePoint()
    {
        x = 0;
        y = 0;
    }
}