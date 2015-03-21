package MonAppliMessagerie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AppliCertificationNode {

	public static void main(String[] args) {

		// Saisie du nom du noeud courant
        System.out.println("AppliNode - Quel est le nom du noeud � cr�er?");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String nodeName = null; 
        try {
        	nodeName = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // initialisation de l'AC
        AppliAC ac = new AppliAC(nodeName);
        ac.initServer(args);
		
        // initialisation de l'AE
        AppliAE ae = new AppliAE(nodeName);
        ae.initServer(args);
        
        // initialisation de l'AV
        AppliAV av = new AppliAV(nodeName);
        av.initServer(args);
	}

}
