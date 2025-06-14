package org.itmo.model;

import java.io.Serializable;

/**
 * Represents a geometric point with X, Y coordinates and radius R.
 * Immutable data model that stores point coordinates and hit status.
 */
public class Point implements Serializable {
    /**
     * X coordinate of the point (immutable)
     */
    private final double X;

    /**
     * Y coordinate of the point (immutable)
     */
    private final double Y;

    /**
     * Radius value associated with the point (immutable)
     */
    private final double R;

    /**
     * Flag indicating whether the point is within the target area
     */
    private boolean IsHit;

    /**
     * Constructs a new Point with specified coordinates and radius.
     *
     * @param X the X coordinate
     * @param Y the Y coordinate
     * @param R the radius value
     */
    public Point(double X, double Y, double R) {
        this.X = X;
        this.Y = Y;
        this.R = R;
    }

    /**
     * Sets whether this point is within the target area.
     *
     * @param IsHit true if point is within area, false otherwise
     */
    public void SetIsHit(boolean IsHit) {
        this.IsHit = IsHit;
    }

    /**
     * Gets the hit status of this point.
     *
     * @return true if point is within the target area, false otherwise
     */
    public boolean GetIsHit() {
        return this.IsHit;
    }

    /**
     * Gets the X coordinate.
     *
     * @return the X coordinate value
     */
    public double getX() {
        return X;
    }

    /**
     * Gets the Y coordinate.
     *
     * @return the Y coordinate value
     */
    public double getY() {
        return Y;
    }

    /**
     * Gets the radius value.
     *
     * @return the radius value
     */
    public double getR() {
        return R;
    }
}
