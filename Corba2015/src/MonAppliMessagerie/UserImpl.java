/**
 * 
 */
package MonAppliMessagerie;

/**
 * @author jeremy
 *
 */
public class UserImpl extends UserPOA {

	@Override
	public String afficherMessage(String message, boolean chiffred) {
		// TODO Auto-generated method stub
		
		if (chiffred)
			System.out.println("Message chiffred reçu : ["+message+"]");
		else
			System.out.println("Message reçu : ["+message+"]");
			
		return null;
	}

}
