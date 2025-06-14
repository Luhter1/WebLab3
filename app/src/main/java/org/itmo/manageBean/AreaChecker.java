package org.itmo.manageBean;

import org.itmo.model.Point;

/**
 * Utility class for checking if points are within a complex geometric area.
 * The area consists of a rectangle, circle sector, and triangle in different quadrants.
 */
public class AreaChecker {

    /**
     * Determines if a point is inside a triangle using barycentric coordinates.
     *
     * @param xA X coordinate of triangle vertex A
     * @param yA Y coordinate of triangle vertex A
     * @param xB X coordinate of triangle vertex B
     * @param yB Y coordinate of triangle vertex B
     * @param xC X coordinate of triangle vertex C
     * @param yC Y coordinate of triangle vertex C
     * @param xP X coordinate of the point to check
     * @param yP Y coordinate of the point to check
     * @return true if point is inside the triangle, false otherwise
     */
    private static boolean isPointInsideTriangle(
            double xA, double yA,
            double xB, double yB,
            double xC, double yC,
            double xP, double yP
    ) {

        double denominator = (yB - yC) * (xA - xC) + (xC - xB) * (yA - yC);

        double lambda1 = ((yB - yC) * (xP - xC) + (xC - xB) * (yP - yC)) / denominator;
        double lambda2 = ((yC - yA) * (xP - xC) + (xA - xC) * (yP - yC)) / denominator;
        double lambda3 = 1.0 - lambda1 - lambda2;


        return lambda1 >= 0 && lambda1 <= 1 &&
                lambda2 >= 0 && lambda2 <= 1 &&
                lambda3 >= 0 && lambda3 <= 1;
    }

    /**
     * Checks if a point with given coordinates is within the target area.
     * The area consists of:
     * - Rectangle in quadrant IV: 0<=X<=R, -R/2<=Y<=0
     * - Circle sector in quadrant III: -R/2<=X<=0, -R/2<=Y<=0, X^2+Y^2<=R^2
     * - Triangle in quadrants I and II: vertices at (0,0), (0,R/2), (-R,0) for Y>=0
     *
     * @param X the X coordinate of the point
     * @param Y the Y coordinate of the point
     * @param R the radius parameter defining the area size
     * @return true if the point is within the target area, false otherwise
     */
    public static boolean IsHit(double X, double Y, double R) {
        if ((0 <= X && X <= R) && (-R / 2 <= Y && Y <= 0)) {
            return true;
        } else if ((-R / 2 <= X && X <= 0) && (-R / 2 <= Y && Y <= 0)) {
            return X * X + Y * Y <= R * R;
        } else if (Y >= 0) {
            return isPointInsideTriangle(
                    0, 0,
                    0, R / 2,
                    -R, 0,
                    X, Y
            );
        } else {
            return false;
        }
    }
}
