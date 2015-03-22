/**
 * 
 */
package MonAppliMessagerie;

import java.util.Hashtable;

/**
 * @author jeremy
 *
 */
public class UserImpl extends UserPOA {
	
	public static org.omg.CosNaming.NamingContext NamingService;
	private String username;
	
	public UserImpl(String username)
	{
		this.username= username ;
		
	}

	@Override
	public String afficherMessage(String sender, String message, boolean chiffred){
		// TODO Auto-generated method stub
		
		if (chiffred)
			{
			Porteur porteur= PorteurHelper.narrow(Tools.findObjByORBName(sender, EntityName.PORTEUR_SERVER));
			Certificat certificatSender = porteur.getCertificatPorteur();
			boolean cheminCertifie;
			try {
				cheminCertifie = verifierCheminCertification(certificatSender);
				if (cheminCertifie)
				{
					Tools.showMessage(Tools.MSG_INFO, "UserImpl","afficherMessage", "Message chiffred reçu : ["+Tools.dechiffrerMessage(message, "")+"]");
					return "ok";
				}
				
				else
				{
					return null;
				}
			} catch (erreur_certif e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (certif_revoque e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
		else
			Tools.showMessage(Tools.MSG_INFO, "UserImpl","afficherMessage", "Message reçu : ["+message+"]");
			
		return "ok";
	}
	
	public void afficherMessageDebug(String message) {
		Tools.showMessage(Tools.MSG_INFO, "UserImpl","afficherMessageDebug", "Message reçu : [" + message+"]");
	}
	
	public boolean verifierPeriode()
	{
		
		return true;
		
	}
	
	public String verifierUsage()
	{
		
		return null;
		
	}
	
	public boolean verifierCheminCertification(Certificat certificatVerifier) throws erreur_certif, certif_revoque
	{
		AV av= AVHelper.narrow(Tools.findObjByORBName(certificatVerifier.IOR_AV, EntityName.AV_SERVER));
		
		Certificat certifAC = av.getCertificatAC();
		
		if (certifAC == null)
			Tools.showMessage(Tools.MSG_DEBUG, "UserImpl","verifierCheminCertification", "le certificat est nul");
		else
			Tools.showMessage(Tools.MSG_DEBUG, "UserImpl","verifierCheminCertification", "le certificat n'est pas nul");
		
		String retourVerification = av.verifierRevocation(certificatVerifier);
		if (retourVerification.equals(VerificationRevocation.CERTIFICAT_REVOQUE.toString()) // si le certificat est révoqué ou suspendu, on bloque l'envoi de message
				|| retourVerification.equals(VerificationRevocation.CERTIFICAT_SUSPENDU.toString()))
		{
			Tools.showMessage(Tools.MSG_DEBUG, "UserImpl","verifierCheminCertification", "certif revoque ou suspendu");
			return false;
		}
		else {
				if (retourVerification.equals(VerificationRevocation.CERTIFICAT_VALIDE_NON_RACINE.toString())) // si le certificat valide mais que l'autorité n'est pas racine alors on contacte l'autorité supérieure
			{
					Tools.showMessage(Tools.MSG_DEBUG, "UserImpl","verifierCheminCertification", "certif valide mais non racine");
				return verifierCheminCertification(certifAC);
			}
			else if (retourVerification.equals(VerificationRevocation.CERTIFICAT_VALIDE_RACINE.toString())) // si le certificat est valide et l'autorité est racine, alors le chemin est complet l'envoi de message est autorisé
			{
				Tools.showMessage(Tools.MSG_DEBUG, "UserImpl","verifierCheminCertification", "certif valide et racine");
				return true;
			}
		}
		return false;
	}
	
	public boolean verifierSignature()
	{
		return true;
	}
	
	public boolean dechiffrerSignature()
	{
		return true;
	}

	public String genererHash()
	{
		return null;
	}
}
