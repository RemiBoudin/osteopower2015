package MonAppliMessagerie;


/**
* MonAppliMessagerie/AEHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from contrat.idl
* samedi 21 f�vrier 2015 14 h 55 CET
*/

abstract public class AEHelper
{
  private static String  _id = "IDL:MonAppliMessagerie/AE:1.0";

  public static void insert (org.omg.CORBA.Any a, MonAppliMessagerie.AE that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static MonAppliMessagerie.AE extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (MonAppliMessagerie.AEHelper.id (), "AE");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static MonAppliMessagerie.AE read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_AEStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, MonAppliMessagerie.AE value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static MonAppliMessagerie.AE narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof MonAppliMessagerie.AE)
      return (MonAppliMessagerie.AE)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      MonAppliMessagerie._AEStub stub = new MonAppliMessagerie._AEStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static MonAppliMessagerie.AE unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof MonAppliMessagerie.AE)
      return (MonAppliMessagerie.AE)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      MonAppliMessagerie._AEStub stub = new MonAppliMessagerie._AEStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
