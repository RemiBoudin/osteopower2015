package MonAppliMessagerie;

import java.util.Hashtable;

public class AVimpl extends AVPOA 
{
	
	private Hashtable <Short, Certificat> listeCertifRevoque;
	private Certificat certificatAC;
	private String idACsrv;
	private String id;
	private Hashtable<Short, Certificat> listeCertifSuspendus;

	public AVimpl(String username)
	{
		this.id = Tools.convertNameToId(username, EntityName.AV_SERVER);
		this.idACsrv = Tools.convertNameToId(username, EntityName.AC_SERVER);
		this.listeCertifRevoque = new Hashtable<Short, Certificat>();
		this.listeCertifSuspendus = new Hashtable<Short, Certificat>();
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
			System.out.println("Certificat Revoque");		
		}
		
		else if (listeCertifSuspendus.containsKey(certifCourant.Num_Unique))
		{
			System.out.println("Certificat Suspendu");
		}
		else
		{
			System.out.println("Certificat non Revoque");
			
			if (certificatAC.IOR_AV == null) //Vérif Si AC RACINE
			{
				System.out.println("AC Racine"); // signaler qu'on est à l'AC racine donc fin de vérification
			}
			
			else
			{
				System.out.println(certificatAC.IOR_AV); // A envoyer à l'user
			}
		}
	}

	@Override
	public void revoquerCertificat(Certificat certificatPorteur,String periode) 
	{
		// TODO Auto-generated method stub
		if (!listeCertifRevoque.containsKey(certificatPorteur.Num_Unique) && periode == null) 
		{
			listeCertifRevoque.put(certificatPorteur.Num_Unique, certificatPorteur);
		}
		
		else if (!listeCertifRevoque.containsKey(certificatPorteur.Num_Unique) && listeCertifSuspendus.containsKey(certificatPorteur.Num_Unique) && periode == null)
		{
			listeCertifSuspendus.remove(certificatPorteur.Num_Unique);
			listeCertifRevoque.put(certificatPorteur.Num_Unique, certificatPorteur);
		}
		
		else if (!listeCertifSuspendus.containsKey(certificatPorteur.Num_Unique) && periode != null)
		{
			listeCertifSuspendus.put(certificatPorteur.Num_Unique, certificatPorteur);
		}
		else
		{
			System.out.println("Certificat déjà révoqué");
		}
	}

}
