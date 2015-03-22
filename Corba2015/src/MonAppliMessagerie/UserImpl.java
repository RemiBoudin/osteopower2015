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

	public UserImpl(String username) {
		this.username = username;

	}

	@Override
	public String afficherMessage(String sender, String message, boolean chiffred) {
		// TODO Auto-generated method stub
		try {
			System.out.println("Nous sommes dans afficherMessageUserImpl avant If");

			if (chiffred) {
				System.out.println("Nous sommes dans afficherMessageUserImpl après If");
				System.out.println("le system out doit s'afficher");
				Porteur porteur = PorteurHelper.narrow(Tools.findObjByORBName(sender, EntityName.PORTEUR_SERVER));
				Certificat certificatSender = porteur.getCertificatPorteur();
				boolean cheminCertifie;
				System.out.println("Nous sommes dans afficherMessageUserImpl avant Try");

				System.out.println("le system out doit s'afficher");
				cheminCertifie = verifierCheminCertification(certificatSender);
				if (cheminCertifie) {
					System.out.println("UserImpl::afficherMessage() : Message chiffred reçu : [" + Tools.dechiffrerMessage(message, "") + "]");
					return "ok";
				}

			} else
				System.out.println("UserImpl::afficherMesssage() : Message reçu : [" + message + "]");

		} catch (Exception e) {
			System.out.println("UserImpl::afficherMessage() : Il faut d'abord générer un certificat avant de pouvoir envoyer des message");
		}
		return "ok";

	}

	public void afficherMessageDebug(String message) {
		System.out.println("Message reçu : [" + message + "]");
	}

	public boolean verifierPeriode() {

		return true;

	}

	public String verifierUsage(Certificat certificatVerifier) {
		
		String usage = certificatVerifier.usage;
		
		switch(usage)
		{
		case "SIGNER":
			return Usages.SIGNER.toString();
			
		case "AUTHENTIFIER":
			return Usages.AUTHENTIFIER.toString();
			
		case "CHIFFRER":
			return Usages.CHIFFRER.toString();
			
		default:
		}

		return null;

	}

	public boolean verifierCheminCertification(Certificat certificatVerifier) {
		AV av = AVHelper.narrow(Tools.findObjByORBName(certificatVerifier.IOR_AV, EntityName.AV_SERVER));

		System.out.println("UserImpl::verifierCheminCertification() : avant av.getCertificat()");

		Certificat certifAC = av.getCertificatAC();

		if (certifAC == null)
			System.out.println("UserImpl::verifierCheminCertification() : le certificat est null");
		else
			System.out.println("UserImpl::verifierCheminCertification() : le certificat n'est pas null");
		
		if(verifierSignature(certificatVerifier))
		{
			System.out.println("Dechiffrer signature avec " + certificatVerifier.Signature + "  "+ certifAC.ClePubClient);
			if (dechiffrerSignature(certificatVerifier, certifAC.ClePubClient))
			{
				String hash = genererHash(certificatVerifier);
				String retourVerification = av.verifierRevocation(certificatVerifier);
					if (retourVerification.equals(VerificationRevocation.CERTIFICAT_REVOQUE.toString()) 
							|| retourVerification.equals(VerificationRevocation.CERTIFICAT_SUSPENDU.toString()))
					{
						System.out.println("Certif revoque ou suspendu");
						return false;
					} 
					else
					{
						if (retourVerification.equals(VerificationRevocation.CERTIFICAT_VALIDE_NON_RACINE.toString())) 
						{
							System.out.println("Certif valide mais non racine");
							return verifierCheminCertification(certifAC);
						}
						else if (retourVerification.equals(VerificationRevocation.CERTIFICAT_VALIDE_RACINE.toString())) 
						{
							System.out.println("Certif valide et racine");
							return true;
						}
					}
			}
			else
				return false;
		}
		else
			return false;
		
		return false;
	}

	public boolean verifierSignature(Certificat certificatEnVerification) {

			return true;
	}

	public boolean dechiffrerSignature(Certificat certificatEnVerification, String PublicKeyAC) {
		
		if (PublicKeyAC.equals(certificatEnVerification.Signature))
			return true;
		else
			return false;
	}

	public String genererHash(Certificat certificatEnVerification) {
		return "hash";
	}
}
