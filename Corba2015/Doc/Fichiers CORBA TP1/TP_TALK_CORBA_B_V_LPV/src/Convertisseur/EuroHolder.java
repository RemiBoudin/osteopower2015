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
public final class EuroHolder implements org.omg.CORBA.portable.Streamable {
public Convertisseur.Euro value;

public EuroHolder () {
}

public EuroHolder (final Convertisseur.Euro _vis_value) {
  this.value = _vis_value;
}

public void _read (final org.omg.CORBA.portable.InputStream input) {
  value = Convertisseur.EuroHelper.read(input);
}

public void _write (final org.omg.CORBA.portable.OutputStream output) {
  Convertisseur.EuroHelper.write(output, value);
}

public org.omg.CORBA.TypeCode _type () {
  return Convertisseur.EuroHelper.type();
}
}
