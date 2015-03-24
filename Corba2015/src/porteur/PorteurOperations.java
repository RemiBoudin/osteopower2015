package porteur;

import certificat.Certificat;

/**
 * Interface definition : Porteur
 * 
 * @author OpenORB Compiler
 */
public interface PorteurOperations
{
    /**
     * Retourne le certificat du client (entité porteur)
     * @return certificat du porteur
     */
    public certificat.Certificat getCertificatPorteur();

}
