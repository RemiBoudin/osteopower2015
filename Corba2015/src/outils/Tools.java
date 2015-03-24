/**
 * 
 */
package outils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.omg.CosNaming.BindingIteratorHolder;
import org.omg.CosNaming.BindingListHolder;
import org.omg.CosNaming.BindingType;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import user.User;

/**
 * @author jeremy Classe faisant office de "boite à outils" pour les différentes
 *         entités du système global. Elle contient les méthodes utiles et
 *         communes.
 */
public class Tools {

	public static User user = null;
	public static final String MSG_DEBUG = "DEBUG";
	public static final String MSG_INFO = "INFO";
	public static final String MSG_ERR = "ERR";

	public static String verbose = null;
	public static String corbalocNamingService = null;
	public static org.omg.CosNaming.NamingContext objDistantNamingService;

	public void init() {
	}

	/**
	 * Donne l'identifiant CORBA normalisé d'une entité é partir de son nom et
	 * du type d'entité
	 * 
	 * @param username
	 *            nom personnalisé de l'entité
	 * @param entity
	 *            type d'entité (AC, AV, Client ...)
	 * @return le nom CORBA normalisé de l'entité
	 */
	public static String convertNameToId(String username, EntityName entity) {
		return (username + "_" + entity.toString());
	}

	/**
	 * Donne la date actuelle au format "yyyy/MM/dd HH:mm:ss"
	 * 
	 * @return la date actuelle
	 */
	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * Donne la signature d'une entité é partir de son identifiant CORBA
	 * normalisé
	 * 
	 * @param id
	 *            identifiant CORBA normalisé
	 * @return signature de l'entité
	 */
	public static String genererSignature(String id) {
		return id;
	}

	/**
	 * Chiffre un message é partir d'une clé publique
	 * 
	 * @param message
	 *            message en clair é chiffrer
	 * @param publicKey
	 *            clé de chiffrement
	 * @return message chiffré
	 */
	public static String chiffrerMessage(String message, String publicKey) {
		return "[crypté]" + message;
	}

	/**
	 * Déchiffre un message é partir d'une clé privée
	 * 
	 * @param message
	 *            message chiffré é déchiffrer
	 * @param privateKey
	 *            clé de déchiffrement
	 * @return message déchiffré, en clair
	 */
	public static String dechiffrerMessage(String message, String privateKey) {
		return message.substring(8);
	}

	public static org.omg.CORBA.Object findObjByORBName(String name, EntityName entity) {
		org.omg.CORBA.Object distantObj = null;

		// Conversion du destinataire en nom ORB
		String receiverORBName = Tools.convertNameToId(name, entity);
		Tools.showMessage(Tools.MSG_DEBUG, "Tools", "findObjByORBName", "receiverORBName créé : " + receiverORBName);

		try {
			// Construction du nom a rechercher
			org.omg.CosNaming.NameComponent[] nameToFind = new org.omg.CosNaming.NameComponent[1];
			nameToFind[0] = new org.omg.CosNaming.NameComponent(receiverORBName, "");

			// Recherche de l'objet aupres du naming service
			distantObj = Tools.objDistantNamingService.resolve(nameToFind);
			Tools.showMessage(Tools.MSG_DEBUG, "Tools", "findObjByORBName", "Objet '" + receiverORBName + "' trouvé auprès du service de noms.");

			return distantObj;

		} catch (Exception e) {
			// e.printStackTrace();
			Tools.showMessage(Tools.MSG_DEBUG, "Tools", "findObjByORBName", "Objet '" + receiverORBName + "' non trouvé auprès du service de noms.");
		}

		return null;
	}

	/**
	 * Génére le jeu de clé publique/privée é partir de l'identifiant CORBA
	 * 
	 * @param id
	 *            identifiant CORBA de l'entité générant son jeu de clé
	 * @return tableau contenant les clés générées [0]= clé publique et [1]=clé
	 *         privée
	 */
	public static String[] generateKeys(String id) {
		String[] keys = new String[2];
		keys[0] = "abc" + id; // Generation de la clé public
		keys[1] = "cba" + id; // Generation de la clé privée
		return keys;
	}

	public static void printContext(NamingContext nc, String parent) {
		try {
			final int batchSize = 1000;
			BindingListHolder bList = new BindingListHolder();
			BindingIteratorHolder bIterator = new BindingIteratorHolder();

			nc.list(batchSize, bList, bIterator);

			for (int i = 0; i < bList.value.length; i++) {
				NameComponent[] name = { bList.value[i].binding_name[0] };
				if (bList.value[i].binding_type == BindingType.ncontext) {
					NamingContext context = NamingContextHelper.narrow(nc.resolve(name));
					printContext(context, parent + name[0].id + ".");
				} else {
					System.out.println(parent + name[0].id);
				}
			}

		} catch (Exception e) {
			System.out.println("ERROR : " + e);
		}
	}

	public static void showMessage(String type, String classe, String methode, String message) {
		if ((Tools.verbose == null) || (Tools.verbose.equals(""))) {
			System.out.println("[" + type + "]\t- [" + classe + "::" + methode + "()]\t=> " + message);
			return;
		}

		if ((Tools.verbose.contains("I")) && (type.equals(Tools.MSG_INFO)))
			System.out.println("[" + type + "]\t- [" + classe + "::" + methode + "()]\t=> " + message);

		if ((Tools.verbose.contains("E")) && (type.equals(Tools.MSG_ERR)))
			System.out.println("[" + type + "]\t- [" + classe + "::" + methode + "()]\t=> " + message);

		if ((Tools.verbose.contains("D")) && (type.equals(Tools.MSG_DEBUG)))
			System.out.println("[" + type + "]\t- [" + classe + "::" + methode + "()]\t=> " + message);

	}
	
	public static void clearScreen() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
	
	/**
	 * Methode d'initialisation du naming service
	 * @param orb
	 */
	public static void initNamingService(org.omg.CORBA.ORB orb) {
		if ((Tools.corbalocNamingService != null) || (!Tools.corbalocNamingService.equals("")))
				Tools.objDistantNamingService = org.omg.CosNaming.NamingContextHelper.narrow(orb.string_to_object(Tools.corbalocNamingService));
	}
}
