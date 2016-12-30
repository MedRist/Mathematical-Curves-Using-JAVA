package exprsBinUn;

import exprs.ExpressionUnaire;

public class Cos extends ExpressionUnaire {
	 
    public double getValeur(double x) {
        return Math.cos(argument.getValeur(x));
    }
 
    public String toString () {
        return "cos(" + argument +")";
    }
}