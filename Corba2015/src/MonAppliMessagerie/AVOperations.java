package MonAppliMessagerie;

/**
 * Interface definition : AV
 * 
 * @author OpenORB Compiler
 */
public interface AVOperations
{
    /**
     * Operation getCertificatAC
     */
    public MonAppliMessagerie.Certificat getCertificatAC();

    /**
     * Operation verifierRevocation
     */
    public void verifierRevocation(MonAppliMessagerie.Certificat certifCourant)
        throws MonAppliMessagerie.erreur_certif, MonAppliMessagerie.certif_revoque;

    /**
     * Operation revoquerCertificat
     */
    public void revoquerCertificat(MonAppliMessagerie.Certificat certificatPorteur, String periode);

}
