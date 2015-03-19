package MonAppliMessagerie;

import java.util.Hashtable;

public class AVimpl extends AVPOA {
	
	private Hashtable <Short, Certificat> listeCertifRevoque;
	private Certificat certificatAC;
	private String id;
	private Hashtable<String, Certificat> listeCertifSuspendus;

	public AVimpl() {
		this.id="AC";
	}
	@Override
	public Certificat getCertificatAC() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void verifierRevocation(Certificat certifCourant)
			throws erreur_certif, certif_revoque {
		// TODO Auto-generated method stub
		
	if (listeCertifRevoque.containsKey(certifCourant.Num_Unique))
	{
		System.out.println("Certificat Valide");
	}
	
	else if (listeCertifSuspendus.containsKey(certifCourant.Num_Unique))
	{
		System.out.println("Certificat Suspendu");
	}
	else
	{
		System.out.println("Certificat non Revoque");
	}
	}

	@Override
	public void revoquerCertificat(Certificat certificatPorteur, short id,
			String periode) {
		// TODO Auto-generated method stub
		if (!listeCertifRevoque.containsKey(id)) 
		{
			listeCertifRevoque.put(id, certificatPorteur);
		}
		else
		{
			System.out.println("Certificat déjà révoqué");
		}
	}

}
