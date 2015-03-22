package MonAppliMessagerie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AppliCertificationNode {

	public static org.omg.CosNaming.NamingContext objDistantNamingService = null;

	public static org.omg.CORBA.ORB objACServerORB = null;
	public static org.omg.CORBA.ORB objAEServerORB = null;
	public static org.omg.CORBA.ORB objAVServerORB = null;

	public static org.omg.CORBA.ORB objACServerORB2 = null;
	public static org.omg.CORBA.ORB objAEServerORB2 = null;
	public static org.omg.CORBA.ORB objAVServerORB2 = null;

	public static void main(String[] args) {
		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("> Mode verbose [I]nfo / [D]ebug / [E]rr / [entrée] : ");
			Tools.verbose = in.readLine();

			// Initialisation de l'ORB
			objACServerORB = org.omg.CORBA.ORB.init(args, null);
			objAEServerORB = org.omg.CORBA.ORB.init(args, null);
			objAVServerORB = org.omg.CORBA.ORB.init(args, null);
			objACServerORB2 = org.omg.CORBA.ORB.init(args, null);
			objAEServerORB2 = org.omg.CORBA.ORB.init(args, null);
			objAVServerORB2 = org.omg.CORBA.ORB.init(args, null);

			// Recuperation du naming service
			AppliCertificationNode.objDistantNamingService = org.omg.CosNaming.NamingContextHelper.narrow(objACServerORB.resolve_initial_references("NameService"));

			initNode(args, "n0", "");
			initNode(args, "n1", "n0");
			initNode(args, "n2", "n0");
			initNode(args, "n3", "n1");
			initNode(args, "n4", "n1");
			initNode(args, "n5", "n2");
			initNode(args, "n6", "n2");
			initNode(args, "n7", "n6");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initNode(String[] args, String nodeName, String nodeParent) {
		try {
			// Initialisation du serveur de l'AC
			AppliAC ac = new AppliAC(nodeName, nodeParent);
			ac.initServer(nodeName, nodeParent);
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

			System.out.println("-> Le niveau de certification \"" + nodeName + "\" a été créé.");

			// Affichage du contenu du service de noms
			System.out.println("-------------------------");
			System.out.println("CONTENU DU NAMING SERVICE");
			System.out.println("-------------------------");
			Tools.printContext(AppliCertificationNode.objDistantNamingService, "");
			System.out.println("-------------------------");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
