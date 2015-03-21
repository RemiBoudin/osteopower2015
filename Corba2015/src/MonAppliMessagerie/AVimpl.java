package MonAppliMessagerie;

import java.util.Hashtable;

public class AVimpl extends AVPOA 
{
	
	private Hashtable <Short, Certificat> listeCertifRevoque;
	private Certificat certificatAC;
	private String nodename;
	private Hashtable<Short, Certificat> listeCertifSuspendus;
	public static org.omg.CosNaming.NamingContext NamingService;

	public AVimpl(String nodeName)
	{
		this.listeCertifRevoque = new Hashtable<Short, Certificat>();
		this.listeCertifSuspendus = new Hashtable<Short, Certificat>();
		this.nodename = nodeName;
	}
	@Override
	public Certificat getCertificatAC() {
		// TODO Auto-generated method stub
		return certificatAC;
	}

	@Override
	public String verifierRevocation(Certificat certifCourant)
			throws erreur_certif, certif_revoque 
	{
		// TODO Auto-generated method stub
		
		if (listeCertifRevoque.containsKey(certifCourant.Num_Unique))
		{
			System.out.println("Certificat Revoque");
			/*User user= UserHelper.narrow(Tools.findObjByORBName(certifCourant.proprietaire, EntityName.USER_SERVER));
			user.afficherMessage(nodename, "ERR CERT : certificat revoque", false);*/
			return VerificationRevocation.CERTIFICAT_REVOQUE.toString();
		}
		
		else if (listeCertifSuspendus.containsKey(certifCourant.Num_Unique))
		{
			System.out.println("Certificat Suspendu");
			return VerificationRevocation.CERTIFICAT_SUSPENDU.toString();
		}
		else
		{
			System.out.println("Certificat non Revoque");
			
			if (certificatAC.IOR_AV == null) //V�rif Si AC RACINE
			{
				System.out.println("AC Racine"); // signaler qu'on est � l'AC racine donc fin de v�rification
				return VerificationRevocation.CERTIFICAT_VALIDE_RACINE.toString();
			}
			
			else
			{
				System.out.println(certificatAC.IOR_AV); // A envoyer � l'user
				return certificatAC.IOR_AV;
			}
		}
	}

	@Override
	public boolean revoquerCertificat(Certificat certificatPorteur,String periode) 
	{
		// TODO Auto-generated method stub
		if (!listeCertifRevoque.containsKey(certificatPorteur.Num_Unique) && periode == null) 
		{
			listeCertifRevoque.put(certificatPorteur.Num_Unique, certificatPorteur);
			System.out.println(this.nodename + "AV - INFO - Certificat de" + certificatPorteur.proprietaire + "ajout� dans la LCR");
			return true;
		}
		
		else if (!listeCertifRevoque.containsKey(certificatPorteur.Num_Unique)
				&& listeCertifSuspendus.containsKey(certificatPorteur.Num_Unique) && periode == null)
		{
			listeCertifSuspendus.remove(certificatPorteur.Num_Unique);
			System.out.println(this.nodename + "AV - INFO - Certificat de" + certificatPorteur.proprietaire + "supprim� de la LCS");
			listeCertifRevoque.put(certificatPorteur.Num_Unique, certificatPorteur);
			System.out.println(this.nodename + "AV - INFO - Certificat de" + certificatPorteur.proprietaire + "ajout� dans la LCR apr�s suppr LCS");
			return true;
		}
		
		else if (!listeCertifSuspendus.containsKey(certificatPorteur.Num_Unique) && periode != null)
		{
			listeCertifSuspendus.put(certificatPorteur.Num_Unique, certificatPorteur);
			System.out.println(this.nodename + "AV - INFO - Certificat de" + certificatPorteur.proprietaire + "ajout� dans la LCS");
			return true;
		}
		else
		{
			System.out.println("Certificat d�j� r�voqu�");
			return true;
		}
	}

}
