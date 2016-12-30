package exprsBinUn;

import exprs.Expression;
import exprs.ExpressionBinaire;
 
 
public class Multiplication extends ExpressionBinaire {
 
    public Multiplication(Expression g, Expression d) {
        super(g, d);
    }
 
    public double getValeur(double x) {
        return gauche.getValeur(x) * droite.getValeur(x);
    }
 
    public String toString() {
        return "(" + gauche + " * " + droite + ")";
    }
 
}