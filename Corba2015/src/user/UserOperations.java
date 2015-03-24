package user;

/**
 * Interface definition : User
 * 
 * @author OpenORB Compiler
 */
public interface UserOperations
{
    /**
     * 
	 * Affiche un message reçu, chiffré ou pas dans le terminal
	 *
     * @param sender Emetteur du message
     * @param message message envoyé
     * @param chiffred état du message : TRUE si chiffré, FALSE si non chiffré
     * @return un string différent de "" si tout s'est bien passé
     */
    public String afficherMessage(String sender, String message, boolean chiffred);

}
