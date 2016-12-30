package exprsBinUn;

import exprs.ExpressionUnaire;
 
 
 
public class Exp extends ExpressionUnaire {
 
    public double getValeur(double x) {
        return Math.exp(argument.getValeur(x));
    }
 
    public String toString () {
        return "exp(" + argument +")";
    }
}