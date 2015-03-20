package MonAppliMessagerie;

import java.util.Hashtable;

public class AEimpl extends AEPOA {
	

	private Hashtable <String, String> listeIdClientPorteur;


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
		if (listeIdClientPorteur.containsKey(certificatPorteur.Num_Unique) && listeIdClientPorteur.get(certificatPorteur.Num_Unique)== mdp)
		{
			listeIdClientPorteur.remove(certificatPorteur.Num_Unique);
		}
		
	}

	@Override
	public void publier(Certificat certificatPorteur) {
		// TODO Auto-generated method stub
		
	}

}
