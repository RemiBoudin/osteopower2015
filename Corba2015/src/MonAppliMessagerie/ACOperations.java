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
    public MonAppliMessagerie.Certificat enregistrer(String clePublique, String proprietaire, String dateExpiration, String usage);

    /**
     * Operation revoquerCertificat
     */
    public boolean revoquerCertificat(MonAppliMessagerie.Certificat certificatPorteur, String periode)
        throws MonAppliMessagerie.certif_revoque;

}
