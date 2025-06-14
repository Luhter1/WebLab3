package org.itmo.manageBean;

import java.util.Optional;

import org.itmo.model.Point;

/**
 * Utility class for validating point coordinates and radius values.
 * Ensures input values are within acceptable ranges before processing.
 */
public class ValidatePoint {
    /**
     * Valid range for X coordinate: [-5, 5]
     */
    private static final double[] ValidX = {-5, 5};

    /**
     * Valid range for Y coordinate: [-5, 5]
     */
    private static final double[] ValidY = {-5, 5};

    /**
     * Valid range for radius: [1, 5]
     */
    private static final double[] ValidR = {1, 5};

    /**
     * Checks if a variable is within the specified valid range (inclusive).
     *
     * @param var      the value to check
     * @param ValidVar array containing [min, max] valid range
     * @return true if var is within the valid range, false otherwise
     */
    private static boolean CheckVar(double var, double[] ValidVar) {
        return ValidVar[0] <= var && var <= ValidVar[1];
    }

    /**
     * Validates if the given coordinates and radius are within acceptable ranges.
     * X and Y must be in range [-5, 5], R must be in range [1, 5].
     *
     * @param x X coordinate to validate
     * @param y Y coordinate to validate
     * @param r radius value to validate
     * @return true if all values are valid, false otherwise
     */
    public static boolean IsValid(double x, double y, double r) {
        return CheckVar(x, ValidX) && CheckVar(y, ValidY) && CheckVar(r, ValidR);
    }
}
