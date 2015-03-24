package av;

import certificat.Certificat;

/**
 * Interface definition : AV
 * 
 * @author OpenORB Compiler
 */
public interface AVOperations
{
	/**
	 * Récupére le certificat de l'AC
	 * @return
	 */
    public certificat.Certificat getCertificatAC();

    /**
     * Méthode vérifiant si le certificat testé est révoqué, suspendu ou valide
     * @param certifCourant 
     * 						Certificat à tester
     * @return
     */
    public String verifierRevocation(certificat.Certificat certifCourant);

    /**
     * Méthode permettant de révoquer un certificat en l'ajoutant à la liste de certificats révoqués ou la liste de certificat suspendus
     * @param certificatPorteur 
     * 						Certificat à révoquer
     * @param periode 
     * 				période à spécifier dans le cas d'une suspension
     * @return
     */
    public boolean revoquerCertificat(certificat.Certificat certificatPorteur, String periode);

}
