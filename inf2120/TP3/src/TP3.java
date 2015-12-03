import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
   public final static File COLLECTION_FILE = new File(FIC_VIDEOS);
   
   
    private ArrayList<Video> collectionVideo1 = new ArrayList<>();
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
       //lecture du fichier binaire COLLECTION_FILE (s'il existe) et chargement
      //de toutes les videos qu'il contient dans la liste collection.
     // chargerCollection();
      //initialisation de l'interface graphique
      init();
   }

   private void init() {

      fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      fenetre.setBounds(LARG_ECRAN / 2 - LARG_FENETRE / 2,
              HAUT_ECRAN / 2 - HAUT_FENETRE / 2,
              LARG_FENETRE, HAUT_FENETRE);
      fenetre.setResizable(false);
      fenetre.setLayout(new FlowLayout());
     
      
      //ajout d'un ecouteur a la fenetre
      fenetre.addWindowListener(this);
      mode = new JLabel("Mode      ");
      collection = new JLabel("Collection");
      collection1 = new JComboBox();
      collection1.setPrototypeDisplayValue("                                   "
              + "                                                              ");
      consultation =new JRadioButton("Consultation    ");
      ajout =new JRadioButton("Ajout  ");
      modification =new JRadioButton("Modification    ");
      recherche =new JRadioButton("Recherche  ");
      groupeMode = new ButtonGroup();
      
      groupeMode.add(consultation);
      groupeMode.add(ajout);
      groupeMode.add(modification);
      groupeMode.add(recherche);
      
      consultation.setSelected(true);
      fenetre.getContentPane().add(collection);
      fenetre.getContentPane().add(collection1);
      fenetre.getContentPane().add(mode);
      fenetre.getContentPane().add(consultation);
      fenetre.getContentPane().add(ajout);
      fenetre.getContentPane().add(modification);
      fenetre.getContentPane().add(recherche);
      //derniere instruction
      fenetre.setVisible(true);
   }
   /**
    * Lit la collection de videos dans le fichier COLLECTION_FILE (s'il existe) 
    * et ajoute toutes les videos presentes dans la liste collection.
    */
   private void chargerCollection() {
      ObjectInputStream stream;
      
      //verifier si le fichier collection.data existe
      if (COLLECTION_FILE.exists()) {
         try {
            stream = new ObjectInputStream(new FileInputStream(COLLECTION_FILE));
            collectionVideo1 = (ArrayList<Video>)stream.readObject();
            stream.close();
         } catch (IOException ioe) {
            JOptionPane.showMessageDialog(fenetre, 
                    "Erreur. Impossible de charger la collection!", "ERREUR", 
                    JOptionPane.ERROR_MESSAGE);
         } catch (ClassNotFoundException cnfe) {
            JOptionPane.showMessageDialog(fenetre, "Erreur.\n" + cnfe.getMessage(),
                    "ERREUR", JOptionPane.ERROR_MESSAGE);
         }
      }
   }


   /**
    * Sauvegarde tous les videos de la liste collection dans le fichier 
    * COLLECTION_FILE.
    */
   private void sauvegarderCollection() {
      ObjectOutputStream out;
      try {
         out = new ObjectOutputStream(new FileOutputStream(COLLECTION_FILE));
         out.writeObject(collection);
         out.close();
       } catch (IOException ioe) {
         JOptionPane.showMessageDialog(fenetre, 
                 "Erreur. Impossible de sauvegarder la collection!", "ERREUR", 
                 JOptionPane.ERROR_MESSAGE);
       }
   }

   @Override
   public void actionPerformed(ActionEvent e) {

   }

   
   @Override
   public void windowClosing(WindowEvent e) {
      //sauvegarder la collection dans FIC_VIDEOS ICI
      // sauvegarderCollection();
   }

   
   public static void main(String[] args) {
      new TP3();
   }

}
