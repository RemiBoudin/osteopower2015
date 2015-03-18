
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
public class EuroPOATie extends EuroPOA {
  private Convertisseur.EuroOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public EuroPOATie (final Convertisseur.EuroOperations _delegate) {
    this._delegate = _delegate;
  }

  public EuroPOATie (final Convertisseur.EuroOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public Convertisseur.EuroOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final Convertisseur.EuroOperations the_delegate) {
    this._delegate = the_delegate;
  }

  public org.omg.PortableServer.POA _default_POA () {
    if (_poa != null) {
      return _poa;
    } 
    else {
      return super._default_POA();
    }
  }

  /**
   * <pre>
   *   double toDevise (in double euro);
   * </pre>
   */
  public double toDevise (double euro) {
    return this._delegate.toDevise(euro);
  }

  /**
   * <pre>
   *   double toEuro (in double devise);
   * </pre>
   */
  public double toEuro (double devise) {
    return this._delegate.toEuro(devise);
  }

  /**
   * <pre>
   *   attribute string devise;
   * </pre>
   */
  public java.lang.String devise () {
    return this._delegate.devise();
  }

  /**
   * <pre>
   *   attribute string devise;
   * </pre>
   */
  public void devise (java.lang.String arg0) {
    this._delegate.devise(arg0);
  }

  /**
   * <pre>
   *   attribute double taux;
   * </pre>
   */
  public double taux () {
    return this._delegate.taux();
  }

  /**
   * <pre>
   *   attribute double taux;
   * </pre>
   */
  public void taux (double arg0) {
    this._delegate.taux(arg0);
  }

}
