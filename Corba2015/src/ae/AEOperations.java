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
     * Operation saveCertificat
     */
    public certificat.Certificat saveCertificat(String clepublique, String proprietaire, String mdp, String dateExpiration, String usage);

    /**
     * Operation revoquer
     */
    public boolean revoquer(certificat.Certificat certificatPorteur, String mdp, String periode);

    /**
     * Operation publier
     */
    public void publier(certificat.Certificat certificatPorteur);

}
