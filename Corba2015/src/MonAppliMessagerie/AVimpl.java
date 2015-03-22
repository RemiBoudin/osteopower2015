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
		
		AC ac = ACHelper.narrow(Tools.findObjByORBName2(nodeName, EntityName.AC_SERVER));
		this.certificatAC = new Certificat(ac.getCertificat());
		
		if (this.certificatAC == null)
			Tools.showMessage(Tools.MSG_DEBUG, "AVimpl", "AVimpl", "le certificat est null");
		else
			Tools.showMessage(Tools.MSG_DEBUG, "AVimpl", "AVimpl", "le certificat n'est pas null");
	}

	@Override
	public Certificat getCertificatAC() {
		
		if (this.certificatAC == null)
			Tools.showMessage(Tools.MSG_DEBUG, "AVimpl", "getCertificat", "le certificat est null");
		else
			Tools.showMessage(Tools.MSG_DEBUG, "AVimpl", "getCertificat", "le certificat n'est pas null");
		
		return new Certificat(this.certificatAC);
	}

	@Override
	public String verifierRevocation(Certificat certifCourant) {
		// TODO Auto-generated method stub

		if (listeCertifRevoque.containsKey(certifCourant.Num_Unique)) {
			Tools.showMessage(Tools.MSG_INFO, "AVimpl", "verifierRevocation", "certificat revoque");
			return VerificationRevocation.CERTIFICAT_REVOQUE.toString();
		}

		else if (listeCertifSuspendus.containsKey(certifCourant.Num_Unique)) {
			Tools.showMessage(Tools.MSG_INFO, "AVimpl", "verifierRevocation", "certificat suspendu");
			return VerificationRevocation.CERTIFICAT_SUSPENDU.toString();
		} else {
			Tools.showMessage(Tools.MSG_INFO, "AVimpl", "verifierRevocation", "certificat non revoque");

			if (certificatAC.IOR_AV.equals("")) // Vérif Si AC RACINE
			{
				Tools.showMessage(Tools.MSG_INFO, "AVimpl", "verifierRevocation", "AC RACINE");
				return VerificationRevocation.CERTIFICAT_VALIDE_RACINE.toString();
			}

			else {
				return certificatAC.IOR_AV;
			}
		}
	}

	@Override
	public boolean revoquerCertificat(Certificat certificatPorteur, String periode) {
		// TODO Auto-generated method stub
		if (!listeCertifRevoque.containsKey(certificatPorteur.Num_Unique) && periode.equals("") && !listeCertifSuspendus.containsKey(certificatPorteur.Num_Unique)) {
			listeCertifRevoque.put(certificatPorteur.Num_Unique, certificatPorteur);
			Tools.showMessage(Tools.MSG_INFO, "AVimpl", "revoquerCertificat", this.nodename + " - Certificat de " + certificatPorteur.proprietaire + " ajouté dans la LCR");
			return true;
		}

		else if (!listeCertifRevoque.containsKey(certificatPorteur.Num_Unique) && listeCertifSuspendus.containsKey(certificatPorteur.Num_Unique) && periode.equals("")) {
			listeCertifSuspendus.remove(certificatPorteur.Num_Unique);
			Tools.showMessage(Tools.MSG_INFO, "AVimpl", "revoquerCertificat", " - Certificat de " + certificatPorteur.proprietaire + " supprimé de la LCS");
			listeCertifRevoque.put(certificatPorteur.Num_Unique, certificatPorteur);
			Tools.showMessage(Tools.MSG_INFO, "AVimpl", "revoquerCertificat", this.nodename + " - Certificat de " + certificatPorteur.proprietaire + " ajouté dans la LCR après suppr LCS");
			return true;
		}

		else if (!listeCertifSuspendus.containsKey(certificatPorteur.Num_Unique) && !periode.equals("") && !listeCertifRevoque.containsKey(certificatPorteur.Num_Unique)) {
			listeCertifSuspendus.put(certificatPorteur.Num_Unique, certificatPorteur);
			Tools.showMessage(Tools.MSG_INFO, "AVimpl", "revoquerCertificat", this.nodename + " - Certificat de " + certificatPorteur.proprietaire + " ajouté dans la LCS");
			return true;
		} else {
			Tools.showMessage(Tools.MSG_INFO, "AVimpl", "revoquerCertificat", this.nodename + " - Certificat déjà révoqué");
			return true;
		}
	}

}
