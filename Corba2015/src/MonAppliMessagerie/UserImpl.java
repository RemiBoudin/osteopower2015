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
	public Certificat certificatSender;
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
			Porteur porteur= (Porteur) UserHelper.narrow(Tools.findObjByORBName(sender, EntityName.PORTEUR_SERVER));
			certificatSender = porteur.getCertificatPorteur();
			boolean cheminCertifie;
			try {
				cheminCertifie = verifierCheminCertification(certificatSender.IOR_AV);
				if (cheminCertifie)
				{
					System.out.println("UserImpl::afficherMessage() : Message chiffred reçu : ["+message+"]");
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
			System.out.println("UserImpl::afficherMesssage() : Message reçu : ["+message+"]");
			
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
	
	public boolean verifierCheminCertification(String IOR_AV_a_contacter) throws erreur_certif, certif_revoque
	{
		AV av= (AV) UserHelper.narrow(Tools.findObjByORBName(IOR_AV_a_contacter, EntityName.AV_SERVER));
		Certificat certifAC = av.getCertificatAC();
		av.verifierRevocation(certificatSender);
		if (av.verifierRevocation(certificatSender) == VerificationRevocation.CERTIFICAT_REVOQUE.toString() // si le certificat est révoqué ou suspendu, on bloque l'envoi de message
				|| av.verifierRevocation(certificatSender) == VerificationRevocation.CERTIFICAT_SUSPENDU.toString())
		{
			return false;
		}
		else {
				if (av.verifierRevocation(certificatSender) == VerificationRevocation.CERTIFICAT_VALIDE_NON_RACINE.toString()) // si le certificat valide mais que l'autorité n'est pas racine alors on contacte l'autorité supérieure
			{
				return verifierCheminCertification(certifAC.IOR_AV);
			}
			else if (av.verifierRevocation(certificatSender) == VerificationRevocation.CERTIFICAT_VALIDE_RACINE.toString()) // si le certificat est valide et l'autorité est racine, alors le chemin est complet l'envoi de message est autorisé
			{
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
