package com.sjl.gwt.progressive.client.example9;

public class Coords
{
    private Integer x;
    private Integer y;
    
    public Coords(Integer anX, Integer aY)
    {
        x = anX;
        y = aY;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    @Override
    public String toString()
    {
        return "coords:[" + x + "," + y + "]";
    }
}
