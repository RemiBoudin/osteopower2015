import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.text.*;

/**
 * Convertisseur Euro - Partie Client
 * @author Yann Steff, Emmanuel Lavinal & Patrice Torguet
 */

/********************************
* Interface Graphique du Client *
********************************/
class InterfaceFrame extends JFrame implements ActionListener {

	private JTextField taux;
	private JTextField devise;
	private java.awt.Container contentPane;
	private FonctionnementPanel p2;
	private AProposDialogue dialogue;
	private JMenuItem itemQuitter, itemAPropos;

	// Constructeur
	public InterfaceFrame() {
		setTitle("Convertisseur Euro");
		setSize(350, 250);
		addWindowListener(new WindowAdapter() {  public void windowClosing(WindowEvent e) {
			System.exit(0); } } );

		this.setResizable(false);

		contentPane = getContentPane();

      	// Insertion d'un menu
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu menu = new JMenu("Fichier");
		menu.setMnemonic('F');
		itemQuitter = new JMenuItem("Quitter");
		itemQuitter.addActionListener(this);
		menu.add(itemQuitter);
		menuBar.add(menu);

		JMenu aide = new JMenu("Aide");
		aide.setMnemonic('A');
		itemAPropos = new JMenuItem("A Propos");
		itemAPropos.addActionListener(this);
		aide.add(itemAPropos);
		menuBar.add(aide);

		// Insertion d'un cadre
		Border bordureParam = BorderFactory.createEtchedBorder();
		Border titreBordureParam = BorderFactory.createTitledBorder(bordureParam,"Parametres");

		JPanel p = new JPanel();
		this.devise = new JTextField(ClientConvertisseurEuro.monEuro.devise(), 10);
		p.add(new JLabel("Devise"));
		p.add(devise);
		devise.addActionListener(this);

		this.taux = new JTextField(""+ClientConvertisseurEuro.monEuro.taux(), 8);
		p.add(new JLabel("Taux"));
		p.add(taux);
		taux.addActionListener(this);
		p.setBorder(titreBordureParam);
		contentPane.add(p, "South");

		p2 = new FonctionnementPanel();
      	contentPane.add(p2,"Center");
   } // constructeur InterfaceFrame

   public void actionPerformed(ActionEvent evt) {

   	java.lang.Object source = evt.getSource();
   	if (source==itemQuitter)
   		System.exit(0);
    else if (source==itemAPropos) {
    		if (dialogue==null)
          dialogue= new AProposDialogue(this);
         dialogue.setVisible(true);
         repaint();
    } else {
    	ClientConvertisseurEuro.monEuro.devise(devise.getText());
	   	try {
	   		double d = Double.parseDouble(taux.getText());
	   		ClientConvertisseurEuro.monEuro.taux(d);
	   	} catch(NumberFormatException nfe) {
	   		ClientConvertisseurEuro.monEuro.taux(0);
	   		taux.setText("0");
	   	}
	   	p2.toDevise.setText("Vers "+ClientConvertisseurEuro.monEuro.devise());
    }
   } // method actionPerformed

} // class InterfaceFrame

class AProposDialogue extends JDialog {

	// Constructeur
	public AProposDialogue(JFrame parent) {

		super(parent, "A propos du convertisseur Euro",true);
		Box b = Box.createVerticalBox();
		b.add(Box.createGlue());
		b.add(new JLabel("Convertisseur Euro 1.1"));
		b.add(new JLabel("Par Yann STEFF Copyright 2000"));
		b.add(new JLabel("Mise a jour Emmanuel Lavinal 2003"));
		b.add(new JLabel("Mise a jour Patrice Torguet 2009"));
		b.add(Box.createGlue());
		getContentPane().add(b, "Center");

		JPanel p2 = new JPanel();
		JButton ok = new JButton("Ok");
		p2.add(ok);
		getContentPane().add(p2, "South");

		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setVisible(false);
			}
         } );

		setSize(250, 150);
   } // constructeur AproposDialogue
} // class AproposDialogue



class FonctionnementPanel extends JPanel implements ActionListener {

	private JButton toEuro;
	public JButton toDevise;
	private JTextField somme;
	private JTextField resultat;

	// Constructeur
	public FonctionnementPanel() {
	 somme = new JTextField("0", 8);
      add(new JLabel("Somme"));
      add(somme);

      toEuro=new JButton("Vers Euro");
      toDevise=new JButton("Vers "+ClientConvertisseurEuro.monEuro.devise());

      //resultat = new DoubleTextField(0, 8);
      resultat = new JTextField(8);
	  add(new JLabel("Resultat"));
      add(resultat);

      add(toEuro);
      add(toDevise);

      toEuro.addActionListener(this);
      toDevise.addActionListener(this);
   }


   public void insertUpdate(DocumentEvent e) {
   }
   public void removeUpdate(DocumentEvent e) {
   }
   public void changedUpdate(DocumentEvent e) {
   }

   public void actionPerformed(ActionEvent evt) {

   	java.lang.Object source = evt.getSource();
   	Double temp = new Double(0);
   	double val = 0;
   	try {
   		val = Double.parseDouble(somme.getText());
   	} catch(NumberFormatException nfe) {
   		somme.setText("0");
   	}

   	if (source == toEuro)
   		temp = new Double(ClientConvertisseurEuro.monEuro.toEuro(val));
   	else if (source == toDevise)
   		temp = new Double(ClientConvertisseurEuro.monEuro.toDevise(val));

   	java.text.NumberFormat nf = java.text.NumberFormat.getNumberInstance();
   	nf.setMaximumFractionDigits(4);
   	String tempf = nf.format(temp);
   	resultat.setText(tempf);
   	} // actionPerformed

} // class FonctionnementPanel

/*******************************
* Client CORBA de l'objet Euro *
*******************************/
public class ClientConvertisseurEuro {

	public static Convertisseur.Euro monEuro;

	public static void main(String args[]) {

		try {

		// Intialisation de l'orb
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args,null);




        // Saisie du nom de l'objet (si utilisation du service de nommage)
        System.out.println("Quel objet Corba voulez-vous contacter ?");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String idObj = in.readLine();

        // Recuperation du naming service
        org.omg.CosNaming.NamingContext nameRoot =
        		org.omg.CosNaming.NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));

        // Construction du nom a rechercher
        org.omg.CosNaming.NameComponent[] nameToFind = new org.omg.CosNaming.NameComponent[1];
         nameToFind[0] = new org.omg.CosNaming.NameComponent(idObj,"");

        // Recherche aupres du naming service
        org.omg.CORBA.Object distantEuro = nameRoot.resolve(nameToFind);
        System.out.println("Objet '" + idObj + "' trouve aupres du service de noms. IOR de l'objet :");
        System.out.println(orb.object_to_string(distantEuro));

        // Utilisation directe de l'IOR (SAUF utilisation du service de nommage)
        // org.omg.CORBA.Object distantEuro = orb.string_to_object("IOR:000000000000001b49444c3a436f6e766572746973736575722f4575726f3a312e30000000000001000000000000007c000102000000000d3137322e31362e39362e35320000cb440000001c00564201000000022f002020000000040000000000000292522da33100000003564953030000000500070801ff000000000000000000000800000000564953000000000100000018000000000001000100000001050100010001010900000000");
        // Casting de l'objet CORBA au type convertisseur euro
        monEuro = Convertisseur.EuroHelper.narrow(distantEuro);

        // Appel de l'interface graphique
        //JFrame frame = new InterfaceFrame();
        //frame.setVisible(true);

        Scanner scanner = new Scanner(System.in);
        monEuro.afficherMessage(scanner.nextLine());
	}
	catch (Exception e) {
		e.printStackTrace();
	}

} // main
} // class ClientConvertisseurEuro

