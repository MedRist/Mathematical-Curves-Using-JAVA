package exprs;

public class Constante implements Expression {
	 
    private double Valeur;
     
    public Constante (double v) {
        Valeur = v;
    }
     
    public double getValeur(double x) {
        return Valeur;
    }
     
    public String toString () {
        return "" + Valeur;
    }
 
}

