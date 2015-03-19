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
			System.out.println("UserImpl::afficherMessage() : Message chiffred reçu : ["+message+"]");
		else
			System.out.println("UserImpl::afficherMesssage() : Message reçu : ["+message+"]");
			
		return "ok";
	}

}
