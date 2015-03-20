package MonAppliMessagerie;

/**
 * Interface definition : AE
 * 
 * @author OpenORB Compiler
 */
public interface AEOperations
{
    /**
     * Operation saveCertificat
     */
    public MonAppliMessagerie.Certificat saveCertificat(String clepublique, String proprietaire, String mdp, String dateExpiration, String usage)
        throws MonAppliMessagerie.erreur_authent;

    /**
     * Operation revoquer
     */
    public void revoquer(MonAppliMessagerie.Certificat certificatPorteur, String mdp, String periode);

    /**
     * Operation publier
     */
    public void publier(MonAppliMessagerie.Certificat certificatPorteur);

}
