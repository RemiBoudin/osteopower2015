package MonAppliMessagerie;


/**
* MonAppliMessagerie/certif_revoqueHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from contrat.idl
* samedi 21 f�vrier 2015 14 h 55 CET
*/

abstract public class certif_revoqueHelper
{
  private static String  _id = "IDL:MonAppliMessagerie/certif_revoque:1.0";

  public static void insert (org.omg.CORBA.Any a, MonAppliMessagerie.certif_revoque that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static MonAppliMessagerie.certif_revoque extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [2];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[0] = new org.omg.CORBA.StructMember (
            "certificat_revoque",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_short);
          _members0[1] = new org.omg.CORBA.StructMember (
            "id",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_exception_tc (MonAppliMessagerie.certif_revoqueHelper.id (), "certif_revoque", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static MonAppliMessagerie.certif_revoque read (org.omg.CORBA.portable.InputStream istream)
  {
    MonAppliMessagerie.certif_revoque value = new MonAppliMessagerie.certif_revoque ();
    // read and discard the repository ID
    istream.read_string ();
    value.certificat_revoque = istream.read_string ();
    value.id = istream.read_short ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, MonAppliMessagerie.certif_revoque value)
  {
    // write the repository ID
    ostream.write_string (id ());
    ostream.write_string (value.certificat_revoque);
    ostream.write_short (value.id);
  }

}
