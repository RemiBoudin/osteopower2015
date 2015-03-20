package MonAppliMessagerie;

/**
 * Interface definition : AC
 * 
 * @author OpenORB Compiler
 */
public interface ACOperations
{
    /**
     * Operation getCertificat
     */
    public MonAppliMessagerie.Certificat getCertificat();

    /**
     * Operation enregistrer
     */
    public void enregistrer(String clePublique, String proprietaire, String dateExpiration, String usage);

    /**
     * Operation revoquerCertificat
     */
    public void revoquerCertificat(MonAppliMessagerie.Certificat certificatPorteur, String periode)
        throws MonAppliMessagerie.certif_revoque;

}
