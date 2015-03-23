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
     * Operation getCertificat
     */
    public certificat.Certificat getCertificat();

    /**
     * Operation enregistrer
     */
    public certificat.Certificat enregistrer(String clePublique, String proprietaire, String dateExpiration, String usage);

    /**
     * Operation revoquerCertificat
     */
    public boolean revoquerCertificat(certificat.Certificat certificatPorteur, String periode);

}
