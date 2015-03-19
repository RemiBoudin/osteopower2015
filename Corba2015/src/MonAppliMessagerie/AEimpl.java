package MonAppliMessagerie;

import java.util.Hashtable;

public class AEimpl extends AEPOA {
	

	private Hashtable <String, Certificat> listeIdClientPorteur;


	@Override
	public Certificat saveCertificat(String clepublique, short id, String mdp,
			String dateExpiration, String usage) throws erreur_authent {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void revoquer(Certificat certificatPorteur, short id, String mdp,
			String periode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void publier(Certificat certificatPorteur) {
		// TODO Auto-generated method stub
		
	}

}
