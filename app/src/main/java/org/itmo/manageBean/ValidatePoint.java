package org.itmo.manageBean;

import java.util.Optional;

import org.itmo.model.Point;

public class ValidatePoint {
    private static final double[] ValidX = {-5, 5};
    private static final double[] ValidY = {-5, 5};
    private static final double[] ValidR = {1, 5};

    private static boolean CheckVar(double var, double[] ValidVar){
        return ValidVar[0]<=var && var<=ValidVar[1];
    }

    public static boolean IsValid(double x, double y, double r){
        return CheckVar( x, ValidX ) && CheckVar( y, ValidY ) && CheckVar( r, ValidR );

    }
}
