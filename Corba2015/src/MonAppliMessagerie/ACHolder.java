package MonAppliMessagerie;

/**
* MonAppliMessagerie/ACHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from contrat.idl
* samedi 21 f�vrier 2015 14 h 55 CET
*/

public final class ACHolder implements org.omg.CORBA.portable.Streamable
{
  public MonAppliMessagerie.AC value = null;

  public ACHolder ()
  {
  }

  public ACHolder (MonAppliMessagerie.AC initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = MonAppliMessagerie.ACHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    MonAppliMessagerie.ACHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return MonAppliMessagerie.ACHelper.type ();
  }

}
