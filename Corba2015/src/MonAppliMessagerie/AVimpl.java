package MonAppliMessagerie;

import java.util.Hashtable;

public class AVimpl extends AVPOA 
{
	
	private Hashtable <Short, Certificat> listeCertifRevoque;
	private Certificat certificatAC;
	private String nodename;
	private Hashtable<Short, Certificat> listeCertifSuspendus;
	public static org.omg.CosNaming.NamingContext NamingService;

	public AVimpl(String nodename, org.omg.CosNaming.NamingContext NamingService)
	{
		
		this.listeCertifRevoque = new Hashtable<Short, Certificat>();
		this.listeCertifSuspendus = new Hashtable<Short, Certificat>();
		this.NamingService= NamingService;
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
			User user= (User)findObjByORBName(certifCourant.proprietaire, EntityName.USER_SERVER);
			user.afficherMessage("ERR CERT : certificat revoque", false);
		}
		
		else if (listeCertifSuspendus.containsKey(certifCourant.Num_Unique))
		{
			System.out.println("Certificat Suspendu");
		}
		else
		{
			System.out.println("Certificat non Revoque");
			
			if (certificatAC.IOR_AV == null) //V�rif Si AC RACINE
			{
				System.out.println("AC Racine"); // signaler qu'on est � l'AC racine donc fin de v�rification
			}
			
			else
			{
				System.out.println(certificatAC.IOR_AV); // A envoyer � l'user
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
			System.out.println(this.id + " - INFO - Certificat de" + certificatPorteur.proprietaire + "ajout� dans la LCR");
		}
		
		else if (!listeCertifRevoque.containsKey(certificatPorteur.Num_Unique)
				&& listeCertifSuspendus.containsKey(certificatPorteur.Num_Unique) && periode == null)
		{
			listeCertifSuspendus.remove(certificatPorteur.Num_Unique);
			System.out.println(this.id + " - INFO - Certificat de" + certificatPorteur.proprietaire + "supprim� de la LCS");
			listeCertifRevoque.put(certificatPorteur.Num_Unique, certificatPorteur);
			System.out.println(this.id + " - INFO - Certificat de" + certificatPorteur.proprietaire + "ajout� dans la LCR apr�s suppr LCS");
		}
		
		else if (!listeCertifSuspendus.containsKey(certificatPorteur.Num_Unique) && periode != null)
		{
			listeCertifSuspendus.put(certificatPorteur.Num_Unique, certificatPorteur);
			System.out.println(this.id + " - INFO - Certificat de" + certificatPorteur.proprietaire + "ajout� dans la LCS");
		}
		else
		{
			System.out.println("Certificat d�j� r�voqu�");
		}
	}

}
