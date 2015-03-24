package ac;

import certificat.Certificat;

/**
 * Interface definition : AC
 * 
 * @author OpenORB Compiler
 */
public interface ACOperations
{
	/**
	 * Récupère le certificat de l'AC
	 * @return
	 */
    public certificat.Certificat getCertificat();

    /**
     * Permet d'enregistrer un certificat en le créant et en le communiquant à l'AE
     * 
     * @param clePublique 
     * 					cléPublique à utiliser pour le certificat
     * @param proprietaire 
     * 					utilisateur demandant un certificat
     * @param dateExpiration 
     * 					date d'Expiration du certificat
     * @param usage 
     * 				spécification de l'usage du certificat
     * @return
     */
    public certificat.Certificat enregistrer(String clePublique, String proprietaire, String dateExpiration, String usage);

    /**
     * Relais de révocation à l'AV
     * 
     * @param certificatPorteur 
     * 						certificat à révoquer
     * @param periode 
     * 				spécification de la période dans le cas d'une suspension
     * @return
     */
    public boolean revoquerCertificat(certificat.Certificat certificatPorteur, String periode);

}
