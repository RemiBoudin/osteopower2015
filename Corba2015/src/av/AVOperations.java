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
     * Operation getCertificatAC
     */
    public certificat.Certificat getCertificatAC();

    /**
     * Operation verifierRevocation
     */
    public String verifierRevocation(certificat.Certificat certifCourant);

    /**
     * Operation revoquerCertificat
     */
    public boolean revoquerCertificat(certificat.Certificat certificatPorteur, String periode);

}
