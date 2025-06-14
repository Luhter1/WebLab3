package org.itmo.controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.SessionContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.UUID;
import java.lang.management.ManagementFactory;

import javax.enterprise.event.Observes;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.itmo.manageBean.DBManager;
import org.itmo.manageBean.AreaChecker;
import org.itmo.manageBean.ValidatePoint;
import org.itmo.manageBean.MissPercentage;
import org.itmo.manageBean.PointCounter;
import org.itmo.model.Point;

import java.sql.*;

/**
 * Session-scoped controller for managing point creation and validation.
 * Handles user input for point coordinates and radius, validates them,
 * and stores valid points in the database.
 */
@Named("makePoint")
@SessionScoped
public class MakePoint implements Serializable {

    private transient PointCounter shotStats;

    /**
     * Unique session identifier generated using UUID
     */
    private final Long id = UUID.randomUUID().getMostSignificantBits();

    /**
     * X coordinate of the point
     */
    private double x;

    /**
     * Y coordinate of the point
     */
    private double y;

    /**
     * Radius value for area checking
     */
    private double r = 1;

    /**
     * Sets the X coordinate.
     *
     * @param x the X coordinate value
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the Y coordinate.
     *
     * @param y the Y coordinate value
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Sets the radius value.
     *
     * @param r the radius value
     */
    public void setR(double r) {
        this.r = r;
    }

    /**
     * Gets the X coordinate.
     *
     * @return the X coordinate value
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the Y coordinate.
     *
     * @return the Y coordinate value
     */
    public double getY() {
        return y;
    }

    /**
     * Gets the radius value.
     *
     * @return the radius value
     */
    public double getR() {
        return r;
    }


    @PostConstruct
    public void init() {
        x = 0;
        y = 0;
        r = 1;


        // Регистрация MBeans
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

            shotStats = new PointCounter();
            MissPercentage missRatio = new MissPercentage(shotStats);

            ObjectName shotStatsName = new ObjectName("org.itmo:type=ShotStats");
            ObjectName missRatioName = new ObjectName("org.itmo:type=MissRatio");

            if (!mbs.isRegistered(shotStatsName)) {
                mbs.registerMBean(shotStats, shotStatsName);
            }
            if (!mbs.isRegistered(missRatioName)) {
                mbs.registerMBean(missRatio, missRatioName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Validates and submits the current point to the database.
     * Only valid points that pass validation are stored.
     */
    public void submit() {
        if (ValidatePoint.IsValid(x, y, r)) {

            boolean isHit = AreaChecker.IsHit(x, y, r);
            if (shotStats != null) {
                shotStats.registerShot(isHit);
            }
            DBManager.insertPointIntoTable(id, x, y, r, isHit);
        }
    }

    /**
     * Retrieves all points associated with this session.
     *
     * @return LinkedList of Point objects for this session
     */
    public LinkedList<Point> getPoints() {
        return DBManager.getPoints(id);
    }
}