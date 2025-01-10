package org.itmo.manageBean;

import org.itmo.model.Point;

public class AreaChecker {

    private static boolean isPointInsideTriangle(
        double xA, double yA, 
        double xB, double yB, 
        double xC, double yC, 
        double xP, double yP
    ) {
        // Вычисляем барицентрические координаты
        double denominator = (yB - yC) * (xA - xC) + (xC - xB) * (yA - yC);

        double lambda1 = ((yB - yC) * (xP - xC) + (xC - xB) * (yP - yC)) / denominator;
        double lambda2 = ((yC - yA) * (xP - xC) + (xA - xC) * (yP - yC)) / denominator;
        double lambda3 = 1.0 - lambda1 - lambda2;

        // Точка внутри треугольника, если все барицентрические координаты в диапазоне [0, 1]
        return lambda1 >= 0 && lambda1 <= 1 &&
                lambda2 >= 0 && lambda2 <= 1 &&
                lambda3 >= 0 && lambda3 <= 1;
    }

    public static boolean IsHit(double X, double Y, double R){
        if( (0<=X && X<=R) && (-R/2<=Y && Y<=0) ){

            return true;

        }else if( (-R/2<=X && X<=0) && (-R/2<=Y && Y<=0) ){

            return X*X + Y*Y <= R*R;

        }else if( Y>=0 ){

            return isPointInsideTriangle(
                0, 0, 
                0, R/2, 
                -R, 0, 
                X, Y
            );   

        }else{

            return false;

        }
        
    }
}