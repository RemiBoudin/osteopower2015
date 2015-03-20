/**
 * 
 */
package MonAppliMessagerie;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jeremy
 *
 */
public class Tools {
	
	public static String convertNameToId(String username, EntityName entity) {
			return (username+"_"+entity.toString());
	}

	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);

	}
	
	public static String genererSignature(String IOR){
		return IOR;
	}
	
	public String chiffrerMessage(String message, String publicKey){
		return "[crypt�]"+message;
	}
	
	public String dechiffrerMessage(String message, String privateKey){
		return message.substring(8);
}
}
