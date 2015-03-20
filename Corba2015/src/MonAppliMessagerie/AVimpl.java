package MonAppliMessagerie;

import java.util.Hashtable;

public class AVimpl extends AVPOA 
{
	
	private Hashtable <Short, Certificat> listeCertifRevoque;
	private Certificat certificatAC;
	private String idACsrv;
	private String id;
	private Hashtable<String, Certificat> listeCertifSuspendus;

	public AVimpl(String username)
	{
		this.id = Tools.convertNameToId(username, EntityName.AV_SERVER);
		this.idACsrv = Tools.convertNameToId(username, EntityName.AC_SERVER);
		this.listeCertifRevoque = new Hashtable<Short, Certificat>();
		this.listeCertifSuspendus = new Hashtable<String, Certificat>();
	}
	@Override
	public Certificat getCertificatAC() {
		// TODO Auto-generated method stub
		return certificatAC;
	}

	@Override
	public void verifierRevocation(Certificat certifCourant)
			throws erreur_certif, certif_revoque 
	{
		// TODO Auto-generated method stub
		
		if (listeCertifRevoque.containsKey(certifCourant.Num_Unique))
		{
			System.out.println("Certificat Valide");
			
			if (certificatAC.IOR_AV == null)
			{
				System.out.println(certificatAC.IOR_AV); // A envoyer � l'user
				System.out.println("AC Racine"); // signaler qu'on est � l'AC racine donc fin de v�rification
			}
			
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
			String periode) 
	{
		// TODO Auto-generated method stub
		if (!listeCertifRevoque.containsKey(id)) 
		{
			listeCertifRevoque.put(id, certificatPorteur);
		}
		
		else if (!listeCertifRevoque.containsKey(id) && listeCertifSuspendus.containsKey(id))
		{
			listeCertifSuspendus.remove(id);
			listeCertifRevoque.put(id, certificatPorteur);
		}
		else
		{
			System.out.println("Certificat d�j� r�voqu�");
		}
	}

}
