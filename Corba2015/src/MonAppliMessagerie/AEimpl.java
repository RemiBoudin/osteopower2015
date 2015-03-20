package MonAppliMessagerie;

import java.util.Hashtable;

public class AEimpl extends AEPOA {

	private Hashtable<String, String> listeIdClientPorteur;
	private String idACsrv;
	private String id;

	public AEimpl(String username) {
		this.id = Tools.convertNameToId(username, EntityName.AE_SERVER);
		this.idACsrv = Tools.convertNameToId(username, EntityName.AC_SERVER);
		this.listeIdClientPorteur = new Hashtable<String, String>();

		this.listeIdClientPorteur.put("titi", "titi");
		this.listeIdClientPorteur.put("toto", "toto");

	}

	@Override
	public Certificat saveCertificat(String clepublique, short id, String mdp,
			String dateExpiration, String usage) throws erreur_authent {
		// Envoi de la demande à l'AC supérieure
		return null;
	}

	@Override
	public void revoquer(Certificat certificatPorteur, short id, String mdp,
			String periode) {

		if (listeIdClientPorteur.containsKey(certificatPorteur.Num_Unique)
				&& listeIdClientPorteur.get(certificatPorteur.Num_Unique) == mdp) {
			listeIdClientPorteur.remove(certificatPorteur.Num_Unique);
		}

	}

	@Override
	public void publier(Certificat certificatPorteur) {
		// TODO Auto-generated method stub

	}

	private boolean authentifier(String user, String mdp) {
		if (this.listeIdClientPorteur.containsKey(user)) {
			if (this.listeIdClientPorteur.get(user).equals(mdp)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private boolean verifierDroits(String user, Certificat certifPorteur, String usage) {
		// si usage certif == usage donné
		
		return true;
	}

}
