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

	public static org.omg.CORBA.ORB objUserServerORB = null;
	public static org.omg.CORBA.ORB objPorteurServerORB = null;
	public static org.omg.CosNaming.NamingContext objDistantNamingService = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// ##########################################
			// # Intialisation de l'environnement CORBA #
			// ##########################################

			// Initialisation de l'ORB
			objUserServerORB = org.omg.CORBA.ORB.init(args, null);
			objPorteurServerORB = org.omg.CORBA.ORB.init(args, null);
			// Recuperation du naming service
			objDistantNamingService = org.omg.CosNaming.NamingContextHelper.narrow(objUserServerORB.resolve_initial_references("NameService"));

			// ############################################
			// # Intialisation des objets Porteur et User #
			// ############################################

			AppliPorteur porteur = new AppliPorteur();
			AppliUser user = new AppliUser();

			System.out.println("## Projet CORBA ##\n");
			System.out.println("-> T'es qui bizu ?");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String clientName = in.readLine();
			System.out.println("-> C'est quoi ton mdp ?");
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

			while (Integer.parseInt(sChoix) != 4) {

				// Affichage du contenu du service de noms
				System.out.println("DEBUT - AFFICHAGE DU NAMING SERVICE - DEBUT");
				System.out.println("-------------------------------------------");
				Tools.printContext(objDistantNamingService, "");
				System.out.println("-------------------------------------------");
				System.out.println("FIN - AFFICHAGE DU NAMING SERVICE - FIN");

				System.out.println("-> Bonjour " + clientName + " et bienvenue dans ce magnifique projet CORBA");
				System.out.println("-> [1] Envoyer un message");
				System.out.println("-> [2] Enregistrer un nouveau certificat");
				System.out.println("-> [3] Révoquer un certificat");
				System.out.println("-> [4] Quitter");
				System.out.println("-> [5] Debug");
				System.out.println("-> Que veux-tu faire ?");

				sChoix = in.readLine();

				switch (Integer.parseInt(sChoix)) {
				case 1: // Cas envoi de message
					System.out.println("-> A qui voulez vous écrire ?");
					String receiverName = in.readLine();
					System.out.println("-> Ecrivez votre message :");
					String message = in.readLine();
					user.repondreToUser(message, receiverName);
					break;
				case 2: // Cas enregistrement de certificat
					System.out.println("-> Usage [signer] [chiffer] [authentifier] ?");
					String usage = in.readLine();
					System.out.println("-> Date d'expiration [aaaa/mm/jj hh:mm:ss] ?");
					String dateExp = in.readLine();
					System.out.println("-> Quelle est votre autorité de certification [au choix] ?");
					String nodeName = in.readLine();
					porteur.enregistrerCertificat(usage, dateExp, nodeName);
					break;
				case 3: // Cas révoquer un certificat
					Certificat certificat = porteur.getCertificat();
					porteur.revoquerCertificat(certificat, clientPassword, null);
					break;
				case 4: // Cas quitter
					System.exit(0);
					break;
				case 5: // Cas debug
					System.out.println("-> /!\\ FONCTION DE DEBUG /!\\");
					// System.out.println("-> /!\\ Destinataire du message ?");
					// String dest = in.readLine();
					// System.out.println("-> /!\\ Message ?");
					// message = in.readLine();
					// user.debug(clientName, message, true, dest);
					System.out.println("Fonction fuckée");
					break;
				default:
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
