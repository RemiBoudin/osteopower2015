package MonAppliMessagerie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AppliCertificationNode {

	public static org.omg.CORBA.ORB objACServerORB = null;
	public static org.omg.CORBA.ORB objAEServerORB = null;
	public static org.omg.CORBA.ORB objAVServerORB = null;
	public static org.omg.CosNaming.NamingContext objDistantNamingService = null;

	public static void main(String[] args) {
		try {
			// ##########################################
			// # Intialisation de l'environnement CORBA #
			// ##########################################

			// Initialisation de l'ORB
			objACServerORB = org.omg.CORBA.ORB.init(args, null);
			objAEServerORB = org.omg.CORBA.ORB.init(args, null);
			objAVServerORB = org.omg.CORBA.ORB.init(args, null);
			// Recuperation du naming service
			AppliCertificationNode.objDistantNamingService = org.omg.CosNaming.NamingContextHelper.narrow(objACServerORB.resolve_initial_references("NameService"));

			// ##################################
			// # Intialisation de l'application #
			// ##################################

			// Saisie du nom du noeud courant
			System.out.println("## Projet CORBA ##\n");
			System.out.println("-> Bonjour et bienvenue dans ce magnifique projet CORBA\n");
			System.out.println("-> Quel est le nom du noeud à créer ?");
			//BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			//String nodeName = null;
			//nodeName = in.readLine();
			String nodeName = "niv1";

			// Initialisation du serveur de l'AC
			AppliAC ac = new AppliAC(nodeName);
			ac.initServer(nodeName);
			Thread threadAC = new Thread(ac);
			threadAC.start();

			// Initialisation du serveur de l'AE
			AppliAE ae = new AppliAE(nodeName);
			ae.initServer(nodeName);
			Thread threadAE = new Thread(ae);
			threadAE.start();

			// Initialisation du serveur de l'AV
			AppliAV av = new AppliAV(nodeName);
			av.initServer(nodeName);
			Thread threadAV = new Thread(av);
			threadAV.start();

			System.out.println("-> Le niveau de certification \""+nodeName+"\" a été créé.");
			System.out.println("-> Les trois autorités AC, AE et AV ont été lancées.");
			

			// Affichage du contenu du service de noms
			System.out.println("-------------------------");
			System.out.println("CONTENU DU NAMING SERVICE");
			System.out.println("-------------------------");
			Tools.printContext(AppliCertificationNode.objDistantNamingService, "");
			System.out.println("-------------------------");
			

			// Recherche du (serveur du porteur) du (destinataire)
			//org.omg.CORBA.Object objDistant = Tools.findObjByORBName("niv1", EntityName.AE_SERVER);
			//AE ae2 = AEHelper.narrow(objDistant);
			
			//ae2.saveCertificat("", "", "", "", "");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
