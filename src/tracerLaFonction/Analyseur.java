package tracerLaFonction;
 
import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
 
 







import exprs.Constante;
import exprs.Expression;
import exprs.ExpressionUnaire;
import exprs.Variable;
import exprsBinUn.Addition;
import exprsBinUn.Cos;
import exprsBinUn.Division;
import exprsBinUn.Exp;
import exprsBinUn.Log;
import exprsBinUn.Multiplication;
import exprsBinUn.Sin;
import exprsBinUn.Soustraction;
 
 
public class Analyseur {
 
    private StreamTokenizer uniteLexical;
     
    public Analyseur (String Texte) throws IOException {
        uniteLexical = new StreamTokenizer (new StringReader(Texte));
        uniteLexical.ordinaryChar('/');
        uniteLexical.ordinaryChar('-');
    }
     
     
    public Expression analyser() throws IOException, ErreurDeSyntaxe {
        uniteLexical.nextToken();
        Expression resultat = analyserExpression();
        if (uniteLexical.ttype != StreamTokenizer.TT_EOF)
            throw new ErreurDeSyntaxe("caractère inattendu à la fin du texte");
        return resultat;
    }
     
     
    private Expression analyserExpression () throws IOException, ErreurDeSyntaxe {
        // traitement des nombres négatifs // on vérifie le premier carac
        boolean Negatif = (uniteLexical.ttype == '-');
        Expression Resultat;
         
        if (Negatif) {
            uniteLexical.nextToken();
            Resultat = new Soustraction (new Constante(0), analyserTerme()); // 0-terme
        }
        else {
            Resultat = analyserTerme();
        }
         
        while (uniteLexical.ttype == '+' || uniteLexical.ttype == '-') {
            boolean EstAdd = (uniteLexical.ttype == '+');
             
            uniteLexical.nextToken();
             
            Expression Terme = analyserTerme();
             
            if (EstAdd) {
                Resultat = new Addition (Resultat, Terme);
            }
            else {
                Resultat = new Soustraction (Resultat, Terme);
            }
        }
             
        return Resultat;
    }
     
     
    private Expression analyserTerme() throws IOException, ErreurDeSyntaxe {
 
        Expression resultat = analyserFacteur();
 
        while (uniteLexical.ttype == '*' || uniteLexical.ttype == '/') {
            boolean estUnProduit = (uniteLexical.ttype == '*');
             
            uniteLexical.nextToken();
             
            Expression facteur = analyserFacteur();
            if (estUnProduit)
                resultat = new Multiplication(resultat, facteur);
            else
                resultat = new Division(resultat, facteur);
        }
        return resultat;
    }
     
     
    private Expression analyserFacteur () throws IOException, ErreurDeSyntaxe {
        Expression Resultat = null;
         
        if (uniteLexical.ttype == StreamTokenizer.TT_NUMBER) {
            Resultat = new Constante (uniteLexical.nval);
            uniteLexical.nextToken();
        }
        else if (uniteLexical.ttype == StreamTokenizer.TT_WORD) {
            String s = uniteLexical.sval.toLowerCase();
             
            if (s.equals("x")) {
                Resultat = new Variable ();
                uniteLexical.nextToken();
            } else {
                if (s.equals("sin")) {
                    Resultat = new Sin();
                } else if (s.equals("cos")) {
                    Resultat = new Cos();
                } else if (s.equals("log")) {
                    Resultat = new Log();
                } else if (s.equals("exp")) {
                    Resultat = new Exp();
                }
                 
                uniteLexical.nextToken();
                 
                if (uniteLexical.ttype != '(')
                    throw new ErreurDeSyntaxe ("( attendue");
                uniteLexical.nextToken();
                ((ExpressionUnaire) Resultat).setArgument(analyserExpression());
                if (uniteLexical.ttype != ')')
                    throw new ErreurDeSyntaxe (") attendue");
                uniteLexical.nextToken();
            }
        }
        else if (uniteLexical.ttype == '(') {
            uniteLexical.nextToken();
            Resultat = analyserExpression();
            if (uniteLexical.ttype != ')')
                throw new ErreurDeSyntaxe (") attendue dans analyseur facteur");
            uniteLexical.nextToken();
             
        }
        else if (uniteLexical.ttype == StreamTokenizer.TT_EOF) {
            throw new ErreurDeSyntaxe ("fin de texte inatendue...");
        }
        else {
            throw new ErreurDeSyntaxe ("Caractère inatendu");
        }
         
        return Resultat;
    }
     
}