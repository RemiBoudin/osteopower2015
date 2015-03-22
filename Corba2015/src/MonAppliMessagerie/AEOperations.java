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
    public MonAppliMessagerie.Certificat saveCertificat(String clepublique, String proprietaire, String mdp, String dateExpiration, String usage);

    /**
     * Operation revoquer
     */
    public boolean revoquer(MonAppliMessagerie.Certificat certificatPorteur, String mdp, String periode);

    /**
     * Operation publier
     */
    public void publier(MonAppliMessagerie.Certificat certificatPorteur);

}
