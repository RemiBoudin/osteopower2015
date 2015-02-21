module MonAppliMessagerie {
	exception erreur_authent {
		string erreur_authentification;
		short id;
	};

	exception erreur_certif {
		string erreur_certification;
		short id;
	};
	
	exception certif_revoque {
		string certificat_revoque;
		short id;		
	};

	struct Certificat {
		string IOR_AV;
		short Num_Unique;
		string ValiditeDebut;
		string ValiditeFin;
		string ClePubClient;
		string usage; // enum?
		string Signature;
	};


	interface AE {
		Certificat saveCertificat(in string clepublique, in short id, in string mdp, in string dateExpiration, in string usage) raises (erreur_authent);
		
		// l'AE authentifie puis relaye la demande de revocation
		void revoquer(in Certificat certificatPorteur, in short id, in string mdp, in string periode);
		
		// l'AE renvoi le certificat créé à l'appli Porteur qui avait demandé l'enregistrement
		void publier(in Certificat certificatPorteur); 
	};

	interface AV {
		Certificat getCertificatAC();
		void verifierRevocation(in Certificat certifCourant) raises(erreur_certif, certif_revoque);

		// AC pousse à l'AV la mise à jour de la LCR
		void revoquerCertificat(in Certificat certificatPorteur, in short id, in string periode); 
	};

	interface AC {
		Certificat getCertificat();
		void enregistrer (in string clePublique, in short idPorteur, in string dateExpiration, in string usage);
		void revoquerCertificat (in Certificat certificatPorteur, in short id, in string periode) raises(certif_revoque);	
	};

	interface User {	
		string afficherMessage(in string message, in boolean chiffré);
	};

	interface Porteur {	
		Certificat getCertificatPorteur();	
		
		// cette méthode affiche les retours liés aux certifications
		void afficherMessage(in string message); 
	};

} // Fin Module