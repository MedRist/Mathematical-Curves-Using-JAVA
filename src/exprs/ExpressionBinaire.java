package exprs;
 
 
public abstract class ExpressionBinaire implements Expression {
 
    protected Expression gauche, droite;
 
    public ExpressionBinaire(Expression g, Expression d) {
        gauche = g;
        droite = d;
    }
 
}


