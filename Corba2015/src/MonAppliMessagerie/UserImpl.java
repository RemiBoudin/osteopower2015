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
		System.out.println("Nous sommes dans afficherMessageUserImpl avant If");
		
		if (chiffred)
			{
			System.out.println("Nous sommes dans afficherMessageUserImpl après If");
			System.out.println("le system out doit s'afficher");
			Porteur porteur= PorteurHelper.narrow(Tools.findObjByORBName(sender, EntityName.PORTEUR_SERVER));
			Certificat certificatSender = porteur.getCertificatPorteur();
			boolean cheminCertifie;
			System.out.println("Nous sommes dans afficherMessageUserImpl avant Try");
			try {
				System.out.println("le system out doit s'afficher");
				cheminCertifie = verifierCheminCertification(certificatSender);
				if (cheminCertifie)
				{
					System.out.println("UserImpl::afficherMessage() : Message chiffred reçu : ["+Tools.dechiffrerMessage(message, "")+"]");
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
			System.out.println("UserImpl::afficherMesssage() : Message non chiffred reçu : ["+message+"]");
			
		return "ok";
	}
	
	public void afficherMessageDebug(String message) {
		System.out.println("Message reçu : [" + message+"]");
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

		System.out.println("UserImpl::verifierCheminCertification() : avant av.getCertificat()");
		
		Certificat certifAC = av.getCertificatAC();
		
		if (certifAC == null)
			System.out.println("UserImpl::verifierCheminCertification() : le certificat est null");
		else
			System.out.println("UserImpl::verifierCheminCertification() : le certificat n'est pas null");
		
		String retourVerification = av.verifierRevocation(certificatVerifier);
		if (retourVerification.equals(VerificationRevocation.CERTIFICAT_REVOQUE.toString()) // si le certificat est révoqué ou suspendu, on bloque l'envoi de message
				|| retourVerification.equals(VerificationRevocation.CERTIFICAT_SUSPENDU.toString()))
		{
			System.out.println("Certif revoque ou suspendu");
			return false;
		}
		else {
				if (retourVerification.equals(VerificationRevocation.CERTIFICAT_VALIDE_NON_RACINE.toString())) // si le certificat valide mais que l'autorité n'est pas racine alors on contacte l'autorité supérieure
			{
					System.out.println("Certif valide mais non racine");
				return verifierCheminCertification(certifAC);
			}
			else if (retourVerification.equals(VerificationRevocation.CERTIFICAT_VALIDE_RACINE.toString())) // si le certificat est valide et l'autorité est racine, alors le chemin est complet l'envoi de message est autorisé
			{
				System.out.println("Certif valide et racine");
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
