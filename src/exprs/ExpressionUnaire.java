package exprs;

public abstract class ExpressionUnaire implements Expression {
    protected Expression argument = null;
 
    public void setArgument(Expression a) {
        argument = a;
    }
}

