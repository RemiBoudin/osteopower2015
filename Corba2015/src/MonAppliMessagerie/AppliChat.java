/**
 * 
 */
package MonAppliMessagerie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.BindingIteratorHolder;
import org.omg.CosNaming.BindingListHolder;
import org.omg.CosNaming.BindingType;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

/**
 * @author jeremy
 *
 */
public class AppliChat {

	//public static org.omg.CORBA.ORB objUserServerORB = null;
	//public static org.omg.CORBA.ORB objPorteurServerORB = null;
	public static org.omg.CORBA.ORB objServerORB = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			if (args.length == 0) {
				System.out.println("Usage : AppliChat [corbaloc_naming_service]");
				System.exit(-1);
			}
			Tools.corbalocNamingService = args[0];
<<<<<<< HEAD
			
=======
>>>>>>> branch 'master' of https://github.com/RemiBoudin/osteopower2015.git
			// ##########################################
			// # Intialisation de l'environnement CORBA #
			// ##########################################

			// Initialisation de l'ORB
			//objUserServerORB = org.omg.CORBA.ORB.init(args, null);
			//objPorteurServerORB = org.omg.CORBA.ORB.init(args, null);
			//objPorteurServerORB = org.omg.CORBA.ORB.init(args, null);
			objServerORB = org.omg.CORBA.ORB.init(args, null);
			// Recuperation du naming service
			//AppliChat.objDistantNamingService = org.omg.CosNaming.NamingContextHelper.narrow(objUserServerORB.string_to_object("corbaloc:iiop:1.2@192.168.43.242:2001/NameService"));
			Tools.initNamingService(objServerORB);
			
			// ############################################
			// # Intialisation des objets Porteur et User #
			// ############################################

			AppliPorteur porteur = new AppliPorteur();
			AppliUser user = new AppliUser();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("## Projet CORBA - Version utilisateur ##\n");
			System.out.print("> Mode verbose [I]nfo / [D]ebug / [E]rr / [entrée] : ");
			Tools.verbose = in.readLine();
			System.out.print("\n> Login : ");
			String clientName = in.readLine();
			System.out.print("> Mdp : ");
			String clientPassword = in.readLine();

			// Initialisation du serveur du porteur
			porteur.initServer(clientName, clientPassword);
			// Lancement du thread du serveur du porteur
			Thread threadPorteur = new Thread(porteur);
			threadPorteur.start();

			// Initialisation du serveur de l'utilisateur
			user.initServer(clientName);
			// Lancement du thread du serveur de l'utilisateur
			Thread threadUser = new Thread(user);
			threadUser.start();

			// ############################################
			// # Initialisation de l'interface graphique #
			// ############################################

			String sChoix = "0";

			while (Integer.parseInt(sChoix) != 5) {
				System.out.println("\n\n\n#########################################");
				System.out.println("##                MENU                 ##");
				System.out.println("#########################################");
				System.out.println("# [1] Envoyer un message");
				System.out.println("# [2] Enregistrer un nouveau certificat");
				System.out.println("# [3] Révoquer un certificat");
				System.out.println("# [4] Afficher contenu du serveur de noms");
				System.out.println("# [5] Quitter\n");
				System.out.print("> Choix : ");

				sChoix = in.readLine();

				switch (Integer.parseInt(sChoix)) {
				case 1: // Cas envoi de message
					System.out.print("-> Nom du destinataire : ");
					String receiverName = in.readLine();
					System.out.print("-> Message : ");
					String message = in.readLine();
					user.repondreToUser(message, receiverName);
					break;
				case 2: // Cas enregistrement de certificat
					/*System.out.println("-> Usage [SIGNER] [CHIFFRER] [AUTHENTIFIER] ?");
					String usage = in.readLine();
					System.out.println("-> Date d'expiration [aaaa/mm/jj hh:mm:ss] ?");
					String dateExp = in.readLine();*/
					System.out.print("-> Autorité de certification [saisie libre] : ");
					String nodeName = in.readLine();
					
					String usage = "SIGNER";
					String dateExp = "2015/12/21 12:18:34";
					
					//String nodeName = "niv1";
					Tools.showMessage(Tools.MSG_INFO, "AppliChat", "main", "usage=signer ; dateExp="+dateExp+" ; nodeName="+nodeName);
					porteur.enregistrerCertificat(usage, dateExp, nodeName);
					break;
				case 3: // Cas révoquer un certificat
					
					Certificat certificat = porteur.getCertificat();
					porteur.revoquerCertificat(certificat, clientPassword, "");
					break;
				case 4: // Cas afficher contenu du serveur de noms
					System.out.println("-------------------------");
					System.out.println("CONTENU DU NAMING SERVICE");
					System.out.println("-------------------------");
					Tools.printContext(Tools.objDistantNamingService, "");
					System.out.println("-------------------------");
					break;
				case 5: // Cas quitter
					System.exit(0);
					break;
				default:
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
