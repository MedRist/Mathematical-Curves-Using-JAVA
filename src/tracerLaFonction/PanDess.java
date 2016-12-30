package tracerLaFonction;
 
import exprs.*;

import java.awt.*;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
 
public class PanDess extends JPanel {

    private Tracer cadre;
 
    public PanDess(Tracer cadre) {
        this.cadre = cadre;
        setBackground(Color.white);
    }
 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
         
        Expression Expr = cadre.getExpression();
        
        if (Expr != null) {
            
            double XMin = cadre.getXmin(), YMin = cadre.getYmin(),XMax = cadre.getXmax(),YMax = cadre.getYmax(),DY   = cadre.getdeltaY(),DX   = cadre.getdeltaX();
             
         
            Dimension d = getSize();
            double Ax = d.width/ (XMax - XMin);
            double Bx = -Ax * XMin;
            double Ay = -d.height / (YMax - YMin);
            double By = - Ay * YMax;
             
            
            g.setColor(new Color(204,204,204));
            for (long i = Math.round(Math.floor(XMin / DX));i <= Math.round(Math.ceil(XMax / DX)); i++) {
                    int Xc = (int) (Ax * i * DX + Bx);
                    g.drawLine(Xc, 0, Xc, d.height);
                    if (i == 0) {
                        Xc += 1;
                        g.drawLine(Xc, 0, Xc, d.height);
                    }
            }
             
            for (long i = Math.round(Math.floor(YMin / DY));
                i <= Math.round(Math.ceil(YMax / DY)); i++) {
                 
                    int Xc = (int) (Ay * i * DY + By);
                    g.drawLine(0, Xc, d.width, Xc);
                    if (i == 0) {
                        Xc += 1;
                        g.drawLine(0, Xc, d.width, Xc);
                    }
            }
             
            
            g.setColor(new Color(81, 9, 237));
            double Saut = (XMax - XMin) / 1000;
            int Xp = 0, Yp = 0;
            for (double i = XMin; i <= XMax; i += Saut) {
                int Xe = (int) (i * Ax + Bx);
                int Ye = (int) (Expr.getValeur(i) * Ay + By);
                if (Xe != 0)
                    g.drawLine(Xp, Yp, Xe, Ye);
                Xp = Xe;
                Yp = Ye;
            }
         
         
        }
    }
    

}