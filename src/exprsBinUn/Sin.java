package exprsBinUn;

import exprs.ExpressionUnaire;
 
 
 
public class Sin extends ExpressionUnaire {
 
    public double getValeur(double x) {
        return Math.sin(argument.getValeur(x));
    }
 
    public String toString () {
        return "sin(" + argument +")";
    }
}