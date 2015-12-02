import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class TP3 extends WindowAdapter implements ActionListener {

   /**
    ***********************************
    * CONSTANTES DE CLASSE
    ***********************************
    */
   //largeur de l'ecran de l'ordinateur
   public final static int LARG_ECRAN
           = Toolkit.getDefaultToolkit().getScreenSize().width;

   //hauteur de l'ecran de l'ordinateur
   public final static int HAUT_ECRAN
           = Toolkit.getDefaultToolkit().getScreenSize().height;

   //Largeur de la fenetre principale
   public final static int LARG_FENETRE = 550;

   //hauteur de la fenetre principale
   public final static int HAUT_FENETRE = 530;

   //titre de la fenetre principale
   public final static String TITRE_FENETRE = "COLLECTION DE VIDEOS";
   
   //couleur de la fenetre principal et des champs non editable
   public final static Color GRIS = new Color(238, 238, 238);

   //fichiers texte contentant la collection de videos
   public final static String FIC_VIDEOS = "videos.txt";
   
   
   

   /**
    ***********************************
    * COMPOSANTS GRAPHIQUES
    ***********************************
    */
   
   //La fenetre principale
   private JFrame fenetre = new JFrame(TITRE_FENETRE);
   // Boutons dans diferent View
   private JButton choix1;
   private JButton choix2;
   
   //Jpanel central
   private JPanel center;
   private JPanel top;
   
   private JComboBox collection1;
   private JComboBox type1;
   private JComboBox evaluation1;
   // Jlabeles
   private JLabel collection;
   private JLabel mode;
   private JLabel titre;
   private JLabel annee;
   private JLabel type;
   private JLabel evaluation;
   private JLabel commentaires;
   private JLabel categorie;
   //boutons radio et groupe pour ces boutons
   private JRadioButton consultation;
   private JRadioButton ajout;
   private JRadioButton modification;
   private JRadioButton recherche;
   private ButtonGroup groupeMode;

   public TP3() {
      init();
   }

   private void init() {

      fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      fenetre.setBounds(LARG_ECRAN / 2 - LARG_FENETRE / 2,
              HAUT_ECRAN / 2 - HAUT_FENETRE / 2,
              LARG_FENETRE, HAUT_FENETRE);
      fenetre.setResizable(false);
      
      //ajout d'un ecouteur a la fenetre
      fenetre.addWindowListener(this);
      collection = new JLabel("Collection");
      collection1 = new JComboBox();
      
      
      
      
      //derniere instruction
      fenetre.setVisible(true);
   }

   
   @Override
   public void actionPerformed(ActionEvent e) {

   }

   
   @Override
   public void windowClosing(WindowEvent e) {
      //sauvegarder la collection dans FIC_VIDEOS ICI
   }

   
   public static void main(String[] args) {
      new TP3();
   }

}
