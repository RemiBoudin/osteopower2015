/**
 * 
 */
package MonAppliMessagerie;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author jeremy
 *
 */
public class AppliPorteur {

	public void initClient() {
		String[] args = null;
		User objDistantUser;
		
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
	        org.omg.CORBA.Object distantUserServer = nameRoot.resolve(nameToFind);
	        System.out.println("Objet '" + idObj + "' trouvé auprès du service de noms. IOR de l'objet :");
	        System.out.println(orb.object_to_string(distantUserServer));

	        // Utilisation directe de l'IOR (SAUF utilisation du service de nommage)
	        // org.omg.CORBA.Object distantEuro = orb.string_to_object("IOR:000000000000001b49444c3a436f6e766572746973736575722f4575726f3a312e30000000000001000000000000007c000102000000000d3137322e31362e39362e35320000cb440000001c00564201000000022f002020000000040000000000000292522da33100000003564953030000000500070801ff000000000000000000000800000000564953000000000100000018000000000001000100000001050100010001010900000000");
	        // Casting de l'objet CORBA au type convertisseur euro
	        objDistantUser = UserHelper.narrow(distantUserServer);

	        // Envoi du message
	        String message = "Bonjour mon bon ami serveur de l'utilisateur !";
	        String ack=objDistantUser.afficherMessage(message, false);
	        System.out.println("ACK reçu : [" +ack+"]");
	        
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initServer() {
		
	}
}
