package org.itmo.model;

import java.io.Serializable;

public class Point implements Serializable {
    private final double X;
    private final double Y;
    private final double R;


    private boolean IsHit;

    public Point(double X, double Y, double R){
        this.X = X;
        this.Y = Y;
        this.R = R;
    }

    public void SetIsHit(boolean IsHit){
        this.IsHit = IsHit;
    }

    public boolean GetIsHit(){
        return this.IsHit;
    }

    public double getX() {
        return X;
    }
    public double getY() {
        return Y;
    }
    public double getR(){
        return R;
    }

}