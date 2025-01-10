package org.itmo.controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.UUID;

import javax.enterprise.event.Observes;
import javax.ejb.SessionContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.itmo.manageBean.DBManager;
import org.itmo.manageBean.AreaChecker;
import org.itmo.manageBean.ValidatePoint;
import org.itmo.model.Point;

import java.sql.*;

@Named("makePoint")
@SessionScoped
public class MakePoint implements Serializable {
    private final Long id = UUID.randomUUID().getMostSignificantBits();
    private double x;
    private double y;
    private double r=1;

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setR(double r) {
        this.r = r;
    }


    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getR(){
        return r;
    }


    public void submit(){

        if ( ValidatePoint.IsValid(x, y, r) ) {

            DBManager.insertPointIntoTable( id, x, y, r, AreaChecker.IsHit(x, y, r) );

        }

    }

    public LinkedList<Point> getPoints() {
        return DBManager.getPoints( id );
    }

}
