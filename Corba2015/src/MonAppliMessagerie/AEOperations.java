package MonAppliMessagerie;


/**
* MonAppliMessagerie/AEOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from contrat.idl
* samedi 21 f�vrier 2015 14 h 55 CET
*/

public interface AEOperations 
{
  MonAppliMessagerie.Certificat saveCertificat (String clepublique, short id, String mdp, String dateExpiration, String usage) throws MonAppliMessagerie.erreur_authent;

  // l'AE authentifie puis relaye la demande de revocation
  void revoquer (MonAppliMessagerie.Certificat certificatPorteur, short id, String mdp, String periode);

  // l'AE renvoi le certificat créé à l'appli Porteur qui avait demandé l'enregistrement
  void publier (MonAppliMessagerie.Certificat certificatPorteur);
} // interface AEOperations
