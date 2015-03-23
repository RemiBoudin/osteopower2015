package MonAppliMessagerie;

import java.util.Hashtable;

public class AVimpl extends AVPOA {

	private Hashtable<Short, Certificat> listeCertifRevoque;
	private Certificat certificatAC;
	private String nodename;
	private Hashtable<Short, Certificat> listeCertifSuspendus;
	public static org.omg.CosNaming.NamingContext NamingService;

	public AVimpl(String nodeName) {
		this.listeCertifRevoque = new Hashtable<Short, Certificat>();
		this.listeCertifSuspendus = new Hashtable<Short, Certificat>();
		this.nodename = nodeName;
		
		AC ac = ACHelper.narrow(Tools.findObjByORBName(nodeName, EntityName.AC_SERVER));
		this.certificatAC = new Certificat(ac.getCertificat());
		
		if (this.certificatAC == null)
			Tools.showMessage(Tools.MSG_DEBUG, "AVimpl", "AVimpl", "le certificat est nul");
		else
			Tools.showMessage(Tools.MSG_DEBUG, "AVimpl", "AVimpl", "le certificat n'est pas nul");
	}

	@Override
	public Certificat getCertificatAC() {
		
		if (this.certificatAC == null)
			Tools.showMessage(Tools.MSG_DEBUG, "AVimpl", "getCertificatAC", "le certificat est nul");
		else
			Tools.showMessage(Tools.MSG_DEBUG, "AVimpl", "getCertificatAC", "le certificat n'est pas nul");
		
		return new Certificat(this.certificatAC);
	}

	@Override
	public String verifierRevocation(Certificat certifCourant) {
		// TODO Auto-generated method stub

		if (listeCertifRevoque.containsKey(certifCourant.Num_Unique)) {
			Tools.showMessage(Tools.MSG_INFO, "AVimpl", "verifierRevocation", "Certificat Revoque");
			return VerificationRevocation.CERTIFICAT_REVOQUE.toString();
		}

		else if (listeCertifSuspendus.containsKey(certifCourant.Num_Unique)) {
			Tools.showMessage(Tools.MSG_INFO, "AVimpl", "verifierRevocation", "Certificat suspendu");
			return VerificationRevocation.CERTIFICAT_SUSPENDU.toString();
		} else {
			Tools.showMessage(Tools.MSG_INFO, "AVimpl", "verifierRevocation", "Certificat non revoque");

			if (certificatAC.IOR_AV.equals("")) // Vérif Si AC RACINE
			{
				Tools.showMessage(Tools.MSG_INFO, "AVimpl", "verifierRevocation", "AC racine");
				return VerificationRevocation.CERTIFICAT_VALIDE_RACINE.toString();
			}

			else {
				return VerificationRevocation.CERTIFICAT_VALIDE_NON_RACINE.toString();
			}
		}
	}

	@Override
	public boolean revoquerCertificat(Certificat certificatPorteur, String periode) {
		if (!listeCertifRevoque.containsKey(certificatPorteur.Num_Unique) && periode.equals("") && !listeCertifSuspendus.containsKey(certificatPorteur.Num_Unique)) {
			listeCertifRevoque.put(certificatPorteur.Num_Unique, certificatPorteur);
			Tools.showMessage(Tools.MSG_INFO, "AVimpl", "revoquerCertificat", this.nodename + " - Certificat de" + certificatPorteur.proprietaire + "ajouté dans la LCR");
			return true;
		}

		else if (!listeCertifRevoque.containsKey(certificatPorteur.Num_Unique) && listeCertifSuspendus.containsKey(certificatPorteur.Num_Unique) && periode.equals("")) {
			listeCertifSuspendus.remove(certificatPorteur.Num_Unique);
			Tools.showMessage(Tools.MSG_INFO, "AVimpl", "revoquerCertificat", this.nodename + " - Certificat de" + certificatPorteur.proprietaire + "supprimé de la LCS");
			listeCertifRevoque.put(certificatPorteur.Num_Unique, certificatPorteur);
			Tools.showMessage(Tools.MSG_INFO, "AVimpl", "revoquerCertificat", this.nodename + " - Certificat de" + certificatPorteur.proprietaire + "ajouté dans la LCR après suppr LCS");
			return true;
		}

		else if (!listeCertifSuspendus.containsKey(certificatPorteur.Num_Unique) && !periode.equals("") && !listeCertifRevoque.containsKey(certificatPorteur.Num_Unique)) {
			listeCertifSuspendus.put(certificatPorteur.Num_Unique, certificatPorteur);
			Tools.showMessage(Tools.MSG_INFO, "AVimpl", "revoquerCertificat", this.nodename + " - Certificat de" + certificatPorteur.proprietaire + "ajouté dans la LCS");
			return true;
		} else {
			Tools.showMessage(Tools.MSG_ERR, "AVimpl", "revoquerCertificat", "Certificat déjà révoqué");
			return true;
		}
	}

}
