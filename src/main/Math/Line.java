package main.Math;

import java.awt.Color;
import java.awt.Point;

public class Line 
{
    public double x1, x2, y1, y2;
    public Color c;
    public Line(double x1, double y1, double x2, double y2, Color c)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.c = c;
    }
    public Line(double x1, double y1, double x2, double y2)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    public Line(int x1, int y1, int x2, int y2, Color c)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.c = c;
    }
    public Line(int x1, int y1, int x2, int y2)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    public Line(Point a, Point b, Color c)
    {
        this.x1 = a.x;
        this.y1 = a.y;
        this.x2 = b.x;
        this.y2 = b.y;
        this.c = c;
    }
}
