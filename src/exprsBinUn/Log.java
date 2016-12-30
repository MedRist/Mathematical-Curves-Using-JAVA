package exprsBinUn;

import exprs.ExpressionUnaire;


 
 
public class Log extends ExpressionUnaire {
 
    public double getValeur(double x) {
        return Math.log(argument.getValeur(x));
    }
 
    public String toString () {
        return "log(" + argument +")";
    }
}