package Convertisseur;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "Convertisseur.idl"
 * <li> <b>IDL Name</b>      ::Convertisseur::Euro
 * <li> <b>Repository Id</b> IDL:Convertisseur/Euro:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface Euro {
  ...
};
 * </pre>
 */
public interface EuroOperations {
  /**
   * <pre>
   *   double toDevise (in double euro);
   * </pre>
   */
  public double toDevise (double euro);

  /**
   * <pre>
   *   double toEuro (in double devise);
   * </pre>
   */
  public double toEuro (double devise);

  /**
   * <pre>
   *   attribute string devise;
   * </pre>
   */
  public java.lang.String devise ();

  /**
   * <pre>
   *   attribute string devise;
   * </pre>
   */
  public void devise (java.lang.String arg0);

  /**
   * <pre>
   *   attribute double taux;
   * </pre>
   */
  public double taux ();

  /**
   * <pre>
   *   attribute double taux;
   * </pre>
   */
  public void taux (double arg0);

}
