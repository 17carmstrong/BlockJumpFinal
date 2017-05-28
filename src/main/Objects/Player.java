package main.Objects;

import main.ComsciFinalProject;
import main.CustomPanel;
import main.Keys;
import main.Math.Collisions;
import main.Math.DoublePoint;

public class Player 
{
    double x = 400, y = -200;
    double xVel = 0, yVel = 0;
    double friction = 0.9, gravity = 1;
    double speed = 4, jumpSpeed = 17, fallSpeed = 30;
    public boolean onSurface = false;
    int jumpsLeft = 2, jumpsMax = 2;
    public void run()
    {
        //on surface checks must be done before this point
        x += xVel;
        if(x < 0)
        {
            x = 0;
        }
        else if(x > 800)
        {
            x = 800;
        }
        y += yVel;
        if(onSurface && !(Keys.B_left || Keys.B_right))
        {
            frictionHorizontal();
            if(Math.abs(xVel) < 0.2)
            {
                xVel = 0;
            }
        }
        
        //temp
        if(!onSurface)
        {
            yVel += gravity;
            if(yVel > fallSpeed)
            {
                yVel = fallSpeed;
            }
        }
        for(Box box : CustomPanel.boxes)
        {
            if(Collisions.Collides2dPoint(x, y, box.x, box.y, box.x + box.width, box.y + box.height))
            {
                CustomPanel.setMenuNum(4);
                return;
            }
        }
        onSurface = false;
        
    }
    public void setOnGround()
    {
        if(y > 0)
        {
            y = 0;
            onSurface = true;
        }
    }
    public void setOnBox(Box b, DoublePoint coll)
    {
        switch(coll.surfaceType)
        {
            case 't':
            {
                setY((coll.y + y) / 2);
                onSurface = true;
                y = b.y - 1;
                yVel = Math.min(b.yVel, yVel);
                jumpsLeft = jumpsMax;
            }
            break;
            case 'b':
            {
                y = (coll.y + y) / 2;
                yVel = b.yVel;
            }
            break;
            case 's':
            {
                x = (coll.x + x) / 2;
                xVel = 0;
            }
            break;
            default:
            {
                System.out.println(coll.surfaceType + " is not a valid collision type");
            }
        }
        setY(getY() + b.yVel);
    }
    public void frictionHorizontal()
    {
        xVel *= friction;
    }
    
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    public void setX(double x)
    {
        this.x = x;
    }
    public void setY(double y)
    {
        this.y = y;
    }
    public double getXVel()
    {
        return xVel;
    }
    public double getYVel()
    {
        return yVel;
    }
    public void stop()
    {
        xVel = 0;
        yVel = 0;
    }
    public void left()
    {
        if(onSurface)
        {
            pureLeft(0.5);
        }
        else
        {
            pureLeft(0.05);
        }
    }
    public void right()
    {
        if(onSurface)
        {
            pureRight(0.5);
        }
        else
        {
            pureRight(0.05);
        }
    }
    public void up()
    {
        if(onSurface)
        {
            yVel = -jumpSpeed;
            onSurface = false;
        }
        else if(jumpsLeft > 0)
        {
            yVel = -jumpSpeed;
            
        if(Keys.B_left && !Keys.B_right)
        {
            pureLeft(1);
        }
        else if(Keys.B_right && !Keys.B_left)
        {
            pureRight(1);
        }
            jumpsLeft--;
        }
    }
    public void down()
    {
        yVel += (fallSpeed - yVel) * 0.4;
    }
    private void pureLeft(double percent)
    {
        xVel += (-speed - xVel) * percent;
    }
    private void pureRight(double percent)
    {
        xVel += (speed - xVel) * percent;
    }
    private void pureUp(double percent)
    {
        
    }
    private void pureDown(double percent)
    {
    }
}