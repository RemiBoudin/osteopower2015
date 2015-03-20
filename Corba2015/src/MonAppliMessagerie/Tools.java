/**
 * 
 */
package MonAppliMessagerie;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

/**
 * @author jeremy Classe faisant office de "bo�te � outils" pour les diff�rentes
 *         entit�s du syst�me global. Elle contient les m�thodes utiles et
 *         communes.
 */
public class Tools {
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
	public static org.omg.CORBA.ORB objDistantORB = null;
	public static org.omg.CosNaming.NamingContext objDistantNamingService = null;
	
	public void init() {
	}
	
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

	public static org.omg.CORBA.Object findObjByORBName(String name, EntityName entity) {
		// Conversion du destinataire en nom ORB
		String receiverORBName = Tools.convertNameToId(name, EntityName.PORTEUR_SERVER);
		
        // Construction du nom a rechercher
        org.omg.CosNaming.NameComponent[] nameToFind = new org.omg.CosNaming.NameComponent[1];
         nameToFind[0] = new org.omg.CosNaming.NameComponent(receiverORBName,"");

        // Recherche de l'objet aupres du naming service
        org.omg.CORBA.Object distantObj = null;
		try {
			distantObj = this.objDistantNamingService.resolve(nameToFind);
		} catch (NotFound | CannotProceed | InvalidName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("AppliPorteur::findObjByORBName() : Objet '" + name + "' trouvé auprès du service de noms.");
        
        return distantObj;
	}
}
