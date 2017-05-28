package main;

import java.awt.Color;

public class Colours 
{
    public static final Color Red = new Color(230, 10, 10);
    public static final Color White = new Color(230, 230, 230);
    public static final Color Black = new Color(0, 0, 0);
    public static Color shadow(int alpha)
    {
        return new Color(0, 0, 0, alpha);
    }
    public static Color greyscale(int brightness)
    {
        return new Color(brightness, brightness, brightness);
    }
    public static Color greyscale(int brightness, int alpha)
    {
        return new Color(brightness, brightness, brightness, alpha);
    }
    
    
    private static int hue = 0;
    private static int r, g, b;
    private static final double hueSpeed = 90;
    public static Color hue(int localhue)
    {
        r = (int)(Math.sin(localhue / hueSpeed) * 127) + 127;
        g = (int)(Math.sin((localhue + 170) / hueSpeed) * 127) + 127;
        b = (int)(Math.sin((localhue + 340) / hueSpeed) * 127) + 127;
        return new Color(r, g, b);
    }
    public static void setHue(int h)
    {
        if(h >= 0 && h < 510)
        {
            hue = h;
        }
    }
    public static void hueShift()
    {
        hue++;
        hue %= 510;
    }
    public static void hueShift(int increment)
    {
        hue += increment;
        hue %= 510;
    }
    public static Color getHueShift()
    {
        r = (int)(Math.sin(hue / hueSpeed) * 127) + 127;
        g = (int)(Math.sin((hue + 170) / hueSpeed) * 127) + 127;
        b = (int)(Math.sin((hue + 340) / hueSpeed) * 127) + 127;
        return new Color(r, g, b);
    }
    public static Color getHueShiftLight()
    {
        r = (int)(Math.sin(hue / hueSpeed) * 127) + 127;
        g = (int)(Math.sin((hue + 170) / hueSpeed) * 127) + 127;
        b = (int)(Math.sin((hue + 340) / hueSpeed) * 127) + 127;
        return new Color(r / 2 + 127, g / 2 + 127, b / 2 + 127);
    }
    public static Color getHueShiftDark()
    {
        r = (int)(Math.sin(hue / hueSpeed) * 127) + 127;
        g = (int)(Math.sin((hue + 170) / hueSpeed) * 127) + 127;
        b = (int)(Math.sin((hue + 340) / hueSpeed) * 127) + 127;
        return new Color(r / 5, g / 5, b / 5);
    }
    public static Color getHueShift2()
    {
        //red
        if(hue < 255)
            r = hue;
        else
            r = 510 - hue;
        //green
        if(hue < 85)
            g = hue + 170;
        else if(hue < 340)
            g = 340 - hue;
        else
            g = hue - 340;
        //blue
        if(hue < 170)
            b = 170 - hue;
        else if(hue < 425)
            b = hue - 170;
        else
            b = 680 - hue;
        return new Color(r, g, b);
    }
    private static int pulse = 0;
    private static final int pulseMax = 255;
    private static final double pulseSpeed = 0.95;
    public static Color getPulseShift()
    {
        r = (int)((Math.sin(hue / hueSpeed) * 127) + 127 + pulse) / 6;
        g = (int)((Math.sin((hue + 170) / hueSpeed) * 127) + 127 + pulse) / 6;
        b = (int)((Math.sin((hue + 340) / hueSpeed) * 127) + 127 + pulse) / 6;
        
        if(pulse > pulseSpeed)
        {
            pulse *= pulseSpeed;
        }
        else
        {
            pulse = 0;
        }
        return new Color(r, g, b);
    }
    public static void pulse()
    {
        pulse = pulseMax;
    }
    
    private static int glitchR1 = 0;
    private static int glitchG1 = 0;
    private static int glitchB1 = 0;
    private static int glitchR2 = 0;
    private static int glitchG2 = 0;
    private static int glitchB2 = 0;
    public static void resetGlitch()
    {
        glitchR1 = 0;
        glitchG1 = 0;
        glitchB1 = 0;
        glitchR2 = 0;
        glitchG2 = 0;
        glitchB2 = 0;
    }
    public static void runGlitch()
    {
        glitchR1 += Math.random() > 0.97?150:0;
        glitchR1 *= Math.random() > 0.97?4:1;
        glitchR1 %= 255;
        glitchG1 += Math.random() > 0.97?150:0;
        glitchG1 *= Math.random() > 0.97?4:1;
        glitchG1 %= 255;
        glitchB1 += Math.random() > 0.97?150:0;
        glitchB1 *= Math.random() > 0.97?4:1;
        glitchB1 %= 255;
        glitchR2 += Math.random() > 0.97?50:0;
        glitchR2 *= Math.random() > 0.97?4:1;
        glitchR2 %= 255;
        glitchG2 += Math.random() > 0.97?50:0;
        glitchG2 *= Math.random() > 0.97?4:1;
        glitchG2 %= 255;
        glitchB2 += Math.random() > 0.97?50:0;
        glitchB2 *= Math.random() > 0.97?4:1;
        glitchB2 %= 255;
    }
    public static Color getGlitch(double chance)
    {
        if(Math.random() > chance)
            r = glitchR1;
        else
            r = glitchR2;
        if(Math.random() > chance)
            g = glitchG1;
        else
            g = glitchG2;
        if(Math.random() > chance)
            b = glitchB1;
        else
            b = glitchB2;
        return new Color(r, g, b);
    }
    public static Color getGlitch1()
    {
        r = glitchR1 % 256;
        g = glitchG1 % 256;
        b = glitchB1 % 256;
        return new Color(r, g, b);
    }
    public static Color getGlitch2()
    {
        r = glitchR2 % 256;
        g = glitchG2 % 256;
        b = glitchB2 % 256;
        return new Color(r, g, b);
    }
    public static void setGlitch(Color c)
    {
        glitchR1 = c.getRed();
        glitchG1 = c.getGreen();
        glitchB1 = c.getBlue();
        glitchR2 = c.getRed();
        glitchG2 = c.getGreen();
        glitchB2 = c.getBlue();
    }
    public static void setGlitch1(Color c)
    {
        glitchR1 = c.getRed();
        glitchG1 = c.getGreen();
        glitchB1 = c.getBlue();
    }
    public static void setGlitch2(Color c)
    {
        glitchR2 = c.getRed();
        glitchG2 = c.getGreen();
        glitchB2 = c.getBlue();
    }
    public static void setGlitch1(int r, int g, int b)
    {
        glitchR1 = r;
        glitchG1 = g;
        glitchB1 = b;
    }
    public static void setGlitch2(int r, int g, int b)
    {
        glitchR2 = r;
        glitchG2 = g;
        glitchB2 = b;
    }
}