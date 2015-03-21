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
	
	public void Userimpl(org.omg.CosNaming.NamingContext NamingService)
	{
		this.NamingService= NamingService;
	}

	@Override
	public String afficherMessage(String sender, String message, boolean chiffred) {
		// TODO Auto-generated method stub
		
		if (chiffred)
			{
			System.out.println("UserImpl::afficherMessage() : Message chiffred reçu : ["+message+"]");
			Porteur porteur= (Porteur) UserHelper.narrow(Tools.findObjByORBName(sender, EntityName.PORTEUR_SERVER));
			Certificat certificatSender = porteur.getCertificatPorteur();
			
			}
		else
			System.out.println("UserImpl::afficherMesssage() : Message reçu : ["+message+"]");
			
		return "ok";
	}
	
	private boolean verifierPeriode()
	{
		return false;
		
	}
	
	private String verifierUsage()
	{
		return null;
		
	}
	
	private void verifierCheminCertification()
	{
		
	}
	
	private void verifierSignature()
	{
		
	}
	
	private void dechiffrerSignature()
	{
		
	}

	private void genererHash()
	{
		
	}
}
