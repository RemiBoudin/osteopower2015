/**
 * 
 */
package MonAppliMessagerie;


import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

/**
 * @author jeremy
 *
 */
public class AppliUser {
	
	public static org.omg.CORBA.ORB objDistantORB = null;
	public static org.omg.CosNaming.NamingContext objDistantNamingService = null;

	public void initClient() {
		
	}
	
	public void initServer(String[] args) {
		try {
	        // Intialisation de l'ORB
	        //************************
	        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args,null);

	        // Gestion du POA
	        //****************
	        // Recuperation du POA
	        POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));

	        // Creation du servant
	        //*********************
	        UserImpl userLocal = new UserImpl();

	        // Activer le servant au sein du POA et recuperer son ID
	        byte[] monEuroId = rootPOA.activate_object(userLocal);

	        // Activer le POA manager
	        rootPOA.the_POAManager().activate();


	        // Enregistrement dans le service de nommage
	        //*******************************************
	        // Recuperation du naming service
	        NamingContext nameRoot=org.omg.CosNaming.NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));

	        // Construction du nom a enregistrer
	        org.omg.CosNaming.NameComponent[] nameToRegister = new org.omg.CosNaming.NameComponent[1];
	        System.out.println("Sous quel nom voulez-vous enregistrer l'objet Corba ?");
	        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	        String nomObj = in.readLine();
	        nameToRegister[0] = new org.omg.CosNaming.NameComponent(nomObj,"");

	        // Enregistrement de l'objet CORBA dans le service de noms
	        nameRoot.rebind(nameToRegister,rootPOA.servant_to_reference(userLocal));
	        System.out.println("==> Nom '"+ nomObj + "' est enregistre dans le service de noms.");

	        String IORServant = orb.object_to_string(rootPOA.servant_to_reference(userLocal));
	        System.out.println("L'objet possede la reference suivante :");
	        System.out.println(IORServant);

	        // Lancement de l'ORB et mise en attente de requete
	        //**************************************************
	        orb.run();

	    }
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private org.omg.CORBA.Object findObjByORBName(String name, EntityName entity) {
		// Conversion du destinataire en nom ORB
		String receiverORBName = Tools.convertName(name, EntityName.PORTEUR_SERVER);
		
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
	
	public void repondreToUser(String message, String receiverName) {

		// #################################################################
		// ## DEMANDE DU CERTIFICAT AU SERVEUR DU PORTEUR DU DESTINATAIRE ##
		// #################################################################
		
		// Recherche du (serveur du porteur) du (destinataire)
		org.omg.CORBA.Object objDistant = this.findObjByORBName(receiverName, EntityName.PORTEUR_SERVER);
		
        // Cast de l'objet distant au format Porteur
        Porteur objDistantPorteurServer = PorteurHelper.narrow(objDistant);
		
        // Demande du certificat au porteur
        Certificat certificatPorteur = objDistantPorteurServer.getCertificatPorteur();

		// ##################################################################
		// ## CHECK DE LA PERIODE DU CERTIFICAT DU PORTEUR DU DESTINATAIRE ##
		// ##################################################################


		// ###############################################################
		// ## CHECK DE L'USAGE DU CERTIFICAT DU PORTEUR DU DESTINATAIRE ##
		// ###############################################################
        
        
	}
	
	public Certificat getCertificat() {
		
		return null;

}
	
}
