package ae;

import certificat.Certificat;

/**
 * Interface definition : AE
 * 
 * @author OpenORB Compiler
 */
public interface AEOperations
{
	/**
	 * Méthode permettant d'enregistrer un certificat sur l'AE après création du certificat par l'AC
	 * @param clepublique
	 * 					 cle publique à utiliser pour le certificat
	 * @param proprietaire
	 * 					 Nom du propriétaire demandant le certificat
	 * @param mdp 
	 * 			mot de passe du propriétaire
	 * @param dateExpiration 
	 * 						date d'expiration de la validité du certificat
	 * @param usage 
	 * 				spécification du type d'usage pour ce certificat
	 * @return
	 */
    public certificat.Certificat saveCertificat(String clepublique, String proprietaire, String mdp, String dateExpiration, String usage);

	/**
	 * Méthode transmettant la demande de révocation à l'AC qui le communiquera à l'AV pour l'ajouter dans la LCR ou la LCS
	 * @param certificatPorteur 
	 * 							certificat à révoquer
	 * @param mdp 
	 * 			mot de passe du proriétaire afin d'autoriser ou non la révocation
	 * @param periode
	 * 				période à spécifier si on ne demande qu'un suspension
	 * @return
	 */
    public boolean revoquer(certificat.Certificat certificatPorteur, String mdp, String periode);

    /**
     * Obsolète
     * @param certificatPorteur
     */
    public void publier(certificat.Certificat certificatPorteur);

}
