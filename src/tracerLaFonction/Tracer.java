package tracerLaFonction;
 
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import exprs.Expression;
 
public class Tracer extends JFrame implements ActionListener{
     
	
	
    private String fonctionInitial = "cos(x)";
    private JTextField [] ChampsParam;
    private JTextField champTextFonction;
    private Expression expresso;
    private JPanel PanDess;
    static String [] lesLabelsGauches = { "X min", "X max", "delta X", "Y min", "Y max", "delta Y" };
    private double[] valsDesParams = { -10.0, +10.0, 1, -10.0, +10.0, 1 };
     
    
    
    
    
    public Tracer () {
        super ("Traceur de courbe des fonctions");
        initDesTextesField();
        ajoutDesComposInterf();
        setSize(700,500);
        setLocationRelativeTo(getParent());
        setDefaultCloseOperation(3);
        setVisible(true);        
        dessiner();
        
    }
     
     
    private void iniDesLabsEtTextsFields(JPanel West_Nord_Nord){
    	 for(int i = 0; i < lesLabelsGauches.length; ++i) {
    		 West_Nord_Nord.add(new JLabel(lesLabelsGauches[i]));
    		 West_Nord_Nord.add(ChampsParam[i]);
         }
    }
    
    
    
    private void initDesTextesField() {
    	
    	 ChampsParam = new JTextField [lesLabelsGauches.length];
         for (int i = 0; i < lesLabelsGauches.length; ++i) {
             ChampsParam [i] = new JTextField(4);
             ChampsParam [i].setText (valsDesParams[i] + "");
             ChampsParam [i].setHorizontalAlignment(JTextField.RIGHT);
         }
		
	}



    private void ajoutDesComposInterf(){
    	 
         champTextFonction = new JTextField ();
         champTextFonction.setText(fonctionInitial);
 
         JPanel Fenetre = new JPanel();
         Fenetre.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
         Fenetre.setLayout(new BorderLayout());
          
         
         setLayout(new BorderLayout());
         JPanel bas = new JPanel ();
         bas.setLayout(new GridLayout(2, 1));
         
         
         JPanel gauche = new JPanel ();
         gauche.setLayout(new BorderLayout ());
         
         JPanel gauche_Nord    = new JPanel();
         gauche_Nord.setLayout(new GridLayout(6, 2, 20, 10));
         gauche_Nord.setBorder(new EmptyBorder( 20, 15, 3, 15 ));
         
         JPanel West_Nord_Nord = new JPanel();
         West_Nord_Nord.setLayout(new BorderLayout());
         
         JPanel panGaucheCentre  = new JPanel();
         panGaucheCentre.setLayout(new FlowLayout());
         
         West_Nord_Nord.add(new JLabel("Paramètres : "), BorderLayout.NORTH);
         West_Nord_Nord.add(gauche_Nord, BorderLayout.CENTER);
         
         
         PanDess = new PanDess (this);
         
         
         bas.add(new JLabel("Éxpression à tracer : f(x)= "));
         bas.add(champTextFonction);
         
         JButton BTracer = new JButton ("Tracer la fonction");
         BTracer.addActionListener(this);
         panGaucheCentre.add(BTracer);
         
         iniDesLabsEtTextsFields(gauche_Nord);
         
         Fenetre.add (bas,BorderLayout.SOUTH);
         Fenetre.add (gauche, BorderLayout.WEST);
         Fenetre.add (PanDess,BorderLayout.CENTER);
         
         
         gauche.add (West_Nord_Nord, BorderLayout.NORTH);
         gauche.add (panGaucheCentre,BorderLayout.CENTER); 
         
         add(Fenetre);
        

    }
    
    
    private void dessiner(){

    	try {
            String s = champTextFonction.getText();
            Analyseur analyseur = new Analyseur (s);
            expresso = analyseur.analyser();
        }
        catch (Exception Exc) {
            JOptionPane.showMessageDialog(this, Exc.getMessage(), "Erreur dans la fonction", JOptionPane.ERROR_MESSAGE);
            return;
        }
         
        for (int i =0; i < lesLabelsGauches.length; ++i) {
            try {
                String s = ChampsParam[i].getText();
                valsDesParams [i] = Double.parseDouble(s);
            }
            catch (Exception Exc){
                JOptionPane.showMessageDialog(this,Exc.getMessage(), "Erreur dans " + lesLabelsGauches[i], JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
       
        PanDess.repaint();
        playsound();
	
    }
    
    private void playsound(){
   	 try {
            // Open an audio input stream.
            URL url = this.getClass().getClassLoader().getResource("applause-01.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
         } catch (Exception e) {
       	  e.printStackTrace();
         }
        
   }
    
	@Override
	public void actionPerformed(ActionEvent arg0) {
		dessiner();
		 
	}
	

    public Expression getExpression () {
        return expresso;
    }
     
    public double getXmin () {
        return valsDesParams[0];
    }
     
    public double getXmax () {
        return valsDesParams[1];
    }
     
    public double getdeltaX () {
        return valsDesParams[2];
    }
     
    public double getYmin () {
        return valsDesParams[3];
    }
     
    public double getYmax () {
        return valsDesParams[4];
    }
     
    public double getdeltaY () {
        return valsDesParams[5];
    }
    
    public static void main(String[] args) {
        
        new Tracer();
    }

     
}