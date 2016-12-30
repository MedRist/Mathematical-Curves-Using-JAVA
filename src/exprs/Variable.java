package exprs;

public class Variable implements Expression {
	 
    public Variable () {
    }
     
    public double getValeur(double x) {
        return x;
    }
 
    public String toString () {
        return "x";
    }
     
}