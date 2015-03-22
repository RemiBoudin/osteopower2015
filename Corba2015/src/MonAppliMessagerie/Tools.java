/**
 * 
 */
package MonAppliMessagerie;

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

/**
 * @author jeremy Classe faisant office de "bo�te � outils" pour les diff�rentes
 *         entit�s du syst�me global. Elle contient les m�thodes utiles et
 *         communes.
 */
public class Tools {

	public static User user = null;
	public static final String MSG_DEBUG = "DEBUG";
	public static final String MSG_INFO = "INFO";
	public static final String MSG_ERR = "ERR";

	public static String verbose = null;

	public void init() {
	}

	/**
	 * Donne l'identifiant CORBA normalis� d'une entit� � partir de son nom et
	 * du type d'entit�
	 * 
	 * @param username
	 *            nom personnalis� de l'entit�
	 * @param entity
	 *            type d'entit� (AC, AV, Client ...)
	 * @return le nom CORBA normalis� de l'entit�
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
	 * Donne la signature d'une entit� � partir de son identifiant CORBA
	 * normalis�
	 * 
	 * @param id
	 *            identifiant CORBA normalis�
	 * @return signature de l'entit�
	 */
	public static String genererSignature(String id) {
		return id;
	}

	/**
	 * Chiffre un message � partir d'une cl� publique
	 * 
	 * @param message
	 *            message en clair � chiffrer
	 * @param publicKey
	 *            cl� de chiffrement
	 * @return message chiffr�
	 */
	public static String chiffrerMessage(String message, String publicKey) {
		return "[crypt�]" + message;
	}

	/**
	 * D�chiffre un message � partir d'une cl� priv�e
	 * 
	 * @param message
	 *            message chiffr� � d�chiffrer
	 * @param privateKey
	 *            cl� de d�chiffrement
	 * @return message d�chiffr�, en clair
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
			distantObj = AppliChat.objDistantNamingService.resolve(nameToFind);
			Tools.showMessage(Tools.MSG_DEBUG, "Tools", "findObjByORBName", "Objet '" + receiverORBName + "' trouvé auprès du service de noms.");

			return distantObj;

		} catch (Exception e) {
			// e.printStackTrace();
			Tools.showMessage(Tools.MSG_DEBUG, "Tools", "findObjByORBName", "Objet '" + receiverORBName + "' non trouvé auprès du service de noms.");
		}

		return null;
	}

	public static org.omg.CORBA.Object findObjByORBName2(String name, EntityName entity) {
		org.omg.CORBA.Object distantObj = null;

		// Conversion du destinataire en nom ORB
		String receiverORBName = Tools.convertNameToId(name, entity);
		Tools.showMessage(Tools.MSG_DEBUG, "Tools", "findObjByORBName2", "receiverORBName créé : " + receiverORBName);

		try {
			// Construction du nom a rechercher
			org.omg.CosNaming.NameComponent[] nameToFind = new org.omg.CosNaming.NameComponent[1];
			nameToFind[0] = new org.omg.CosNaming.NameComponent(receiverORBName, "");

			// Recherche de l'objet aupres du naming service
			distantObj = AppliCertificationNode.objDistantNamingService.resolve(nameToFind);
			Tools.showMessage(Tools.MSG_DEBUG, "Tools", "findObjByORBName2", receiverORBName + " trouvé auprès du service de noms.");

			return distantObj;

		} catch (Exception e) {
			e.printStackTrace();
			Tools.showMessage(Tools.MSG_DEBUG, "Tools", "findObjByORBName2", receiverORBName + " non trouvé auprès du service de noms.");
		}

		return null;
	}

	/**
	 * G�n�re le jeu de cl� publique/priv�e � partir de l'identifiant CORBA
	 * 
	 * @param id
	 *            identifiant CORBA de l'entit� g�n�rant son jeu de cl�
	 * @return tableau contenant les cl�s g�n�r�es [0]= cl� publique et [1]=cl�
	 *         priv�e
	 */
	public static String[] generateKeys(String id) {
		String[] keys = new String[2];
		keys[0] = "abc" + id; // Generation de la cl� public
		keys[1] = "cba" + id; // Generation de la cl� priv�e
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
}
