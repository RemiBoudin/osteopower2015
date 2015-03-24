/**
 * 
 */
package user;

import java.util.Hashtable;

import outils.EntityName;
import outils.Tools;
import outils.Usages;
import outils.VerificationRevocation;
import certificat.Certificat;
import porteur.Porteur;
import porteur.PorteurHelper;
import av.AV;
import av.AVHelper;

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
			Tools.showMessage(Tools.MSG_DEBUG, "UserImpl", "afficherMessage", "Nous sommes dans afficherMessageUserImpl avant If");

			if (chiffred) {
				Tools.showMessage(Tools.MSG_DEBUG, "UserImpl", "afficherMessage", "Nous sommes après If");
				Porteur porteur = PorteurHelper.narrow(Tools.findObjByORBName(sender, EntityName.PORTEUR_SERVER));
				Certificat certificatSender = porteur.getCertificatPorteur();
				boolean cheminCertifie;
				Tools.showMessage(Tools.MSG_DEBUG, "UserImpl", "afficherMessage", "Nous sommes avant Try");
				cheminCertifie = verifierCheminCertification(certificatSender);
				if (cheminCertifie) {
					Tools.showMessage(Tools.MSG_INFO, "UserImpl", "afficherMessage", "Message chiffred reçu de la part de "+sender+" : [" + Tools.dechiffrerMessage(message, "") + "]");
					return "ok";
				}

			} else
				Tools.showMessage(Tools.MSG_INFO, "UserImpl", "afficherMessage", "Message reçu de la part de"+sender+": [" + message + "]");

		} catch (Exception e) {
			Tools.showMessage(Tools.MSG_ERR, "UserImpl", "afficherMessage", "Il faut d'abord générer un certificat avant de pouvoir envoyer des message");
		}
		return "ok";

	}

	public void afficherMessageDebug(String message) {
		Tools.showMessage(Tools.MSG_INFO, "UserImpl", "afficherMessageDebug", "Message reçu : [" + message + "]");
	}

	public boolean verifierPeriode() {

		return true;

	}

	public String verifierUsage(Certificat certificatVerifier) {

		String usage = certificatVerifier.usage;

		switch (usage) {
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

		Certificat certifAC = av.getCertificatAC();

		if (certifAC == null)
			Tools.showMessage(Tools.MSG_DEBUG, "UserImpl", "verifierCheminCertification", "Le certificat est nul");
		else
			Tools.showMessage(Tools.MSG_DEBUG, "UserImpl", "verifierCheminCertification", "Le certificat n'est pas nul");

		if (verifierSignature(certificatVerifier)) {
			Tools.showMessage(Tools.MSG_DEBUG, "UserImpl", "verifierCheminCertification", certificatVerifier.Signature + "  " + certifAC.ClePubClient);
			if (dechiffrerSignature(certificatVerifier, certifAC.ClePubClient)) {
				String hash = genererHash(certificatVerifier);

				String retourVerification = av.verifierRevocation(certificatVerifier);
				Tools.showMessage(Tools.MSG_DEBUG, "UserImpl", "verifierCheminCertification", "retourVerif=" + retourVerification);

				if (retourVerification.equals(VerificationRevocation.CERTIFICAT_REVOQUE.toString()) || retourVerification.equals(VerificationRevocation.CERTIFICAT_SUSPENDU.toString())) {
					Tools.showMessage(Tools.MSG_INFO, "UserImpl", "verifierCheminCertification", "Certif revoque ou suspendu");
					return false;
				} else {
					if (retourVerification.equals(VerificationRevocation.CERTIFICAT_VALIDE_NON_RACINE.toString())) {
						Tools.showMessage(Tools.MSG_INFO, "UserImpl", "verifierCheminCertification", "Certif valide mais non racine");
						return verifierCheminCertification(certifAC);
					} else if (retourVerification.equals(VerificationRevocation.CERTIFICAT_VALIDE_RACINE.toString())) {
						Tools.showMessage(Tools.MSG_INFO, "UserImpl", "verifierCheminCertification", "Certif valide et racine");
						return true;
					} else {
						Tools.showMessage(Tools.MSG_ERR, "UserImpl", "verifierCheminCertification", "CAS IMPROBABLE !!!!");
						return false;
					}
				}
			} else
				Tools.showMessage(Tools.MSG_DEBUG, "UserImpl", "verifierCheminCertification", "AVANT DERNIER FALSE");
			return false;
		} else
			Tools.showMessage(Tools.MSG_DEBUG, "UserImpl", "verifierCheminCertification", "AVANT DERNIER FALSE");
		return false;

	}

	public boolean verifierSignature(Certificat certificatEnVerification) {

		return true;
	}

	public boolean dechiffrerSignature(Certificat certificatEnVerification, String PublicKeyAC) {

		if (PublicKeyAC.equals(certificatEnVerification.Signature)) {
			Tools.showMessage(Tools.MSG_INFO, "UserImpl", "dechiffrerSignature", "Dechiffrement OK - " + certificatEnVerification.Signature + " VS " + PublicKeyAC);
			return true;
		} else {
			Tools.showMessage(Tools.MSG_ERR, "UserImpl", "dechiffrerSignature", "Dechiffrement NON OK - " + certificatEnVerification.Signature + " VS " + PublicKeyAC);
			return false;
		}
	}

	public String genererHash(Certificat certificatEnVerification) {
		return "hash";
	}
}
