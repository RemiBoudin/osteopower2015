/**
 * 
 */
package MonAppliMessagerie;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jeremy Classe faisant office de "boîte à outils" pour les différentes
 *         entités du système global. Elle contient les méthodes utiles et
 *         communes.
 */
public class Tools {
	/**
	 * Donne l'identifiant CORBA normalisé d'une entité à partir de son nom et
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
	 * Donne la signature d'une entité à partir de son identifiant CORBA
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
	 * Chiffre un message à partir d'une clé publique
	 * 
	 * @param message
	 *            message en clair à chiffrer
	 * @param publicKey
	 *            clé de chiffrement
	 * @return message chiffré
	 */
	public static String chiffrerMessage(String message, String publicKey) {
		return "[crypté]" + message;
	}

	/**
	 * Déchiffre un message à partir d'une clé privée
	 * 
	 * @param message
	 *            message chiffré à déchiffrer
	 * @param privateKey
	 *            clé de déchiffrement
	 * @return message déchiffré, en clair
	 */
	public static String dechiffrerMessage(String message, String privateKey) {
		return message.substring(8);
	}

	/**
	 * Génère le jeu de clé publique/privée à partir de l'identifiant CORBA
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
}
