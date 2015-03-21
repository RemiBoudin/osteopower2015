package MonAppliMessagerie;

/**
 * Interface definition : User
 * 
 * @author OpenORB Compiler
 */
public interface UserOperations
{
    /**
     * Operation afficherMessage
     * @throws certif_revoque 
     * @throws erreur_certif 
     */
    public String afficherMessage(String sender, String message, boolean chiffred) throws erreur_certif, certif_revoque;

}
