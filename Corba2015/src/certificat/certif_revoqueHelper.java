package certificat;

/** 
 * Helper class for : certif_revoque
 *  
 * @author OpenORB Compiler
 */ 
public class certif_revoqueHelper
{
    private static final boolean HAS_OPENORB;
    static {
        boolean hasOpenORB = false;
        try {
            Thread.currentThread().getContextClassLoader().loadClass("org.openorb.CORBA.Any");
            hasOpenORB = true;
        }
        catch(ClassNotFoundException ex) {
        }
        HAS_OPENORB = hasOpenORB;
    }
    /**
     * Insert certif_revoque into an any
     * @param a an any
     * @param t certif_revoque value
     */
    public static void insert(org.omg.CORBA.Any a, certificat.certif_revoque t)
    {
        a.insert_Streamable(new certificat.certif_revoqueHolder(t));
    }

    /**
     * Extract certif_revoque from an any
     * @param a an any
     * @return the extracted certif_revoque value
     */
    public static certificat.certif_revoque extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        if (HAS_OPENORB && a instanceof org.openorb.CORBA.Any) {
            // streamable extraction. The jdk stubs incorrectly define the Any stub
            org.openorb.CORBA.Any any = (org.openorb.CORBA.Any)a;
            try {
                org.omg.CORBA.portable.Streamable s = any.extract_Streamable();
                if(s instanceof certificat.certif_revoqueHolder)
                    return ((certificat.certif_revoqueHolder)s).value;
            } catch (org.omg.CORBA.BAD_INV_ORDER ex) {
            }
            certificat.certif_revoqueHolder h = new certificat.certif_revoqueHolder(read(a.create_input_stream()));
            a.insert_Streamable(h);
            return h.value;
        }
        return read(a.create_input_stream());
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;
    private static boolean _working = false;

    /**
     * Return the certif_revoque TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            synchronized(org.omg.CORBA.TypeCode.class) {
                if (_tc != null)
                    return _tc;
                if (_working)
                    return org.omg.CORBA.ORB.init().create_recursive_tc(id());
                _working = true;
                org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
                org.omg.CORBA.StructMember []_members = new org.omg.CORBA.StructMember[2];

                _members[0] = new org.omg.CORBA.StructMember();
                _members[0].name = "certificat_revoque";
                _members[0].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_string);
                _members[1] = new org.omg.CORBA.StructMember();
                _members[1].name = "id";
                _members[1].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_short);
                _tc = orb.create_exception_tc(id(),"certif_revoque",_members);
                _working = false;
            }
        }
        return _tc;
    }

    /**
     * Return the certif_revoque IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:MonAppliMessagerie/certif_revoque:1.0";

    /**
     * Read certif_revoque from a marshalled stream
     * @param istream the input stream
     * @return the readed certif_revoque value
     */
    public static certificat.certif_revoque read(org.omg.CORBA.portable.InputStream istream)
    {
        certificat.certif_revoque new_one = new certificat.certif_revoque();

        if (!istream.read_string().equals(id()))
         throw new org.omg.CORBA.MARSHAL();
        new_one.certificat_revoque = istream.read_string();
        new_one.id = istream.read_short();

        return new_one;
    }

    /**
     * Write certif_revoque into a marshalled stream
     * @param ostream the output stream
     * @param value certif_revoque value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, certificat.certif_revoque value)
    {
        ostream.write_string(id());
        ostream.write_string(value.certificat_revoque);
        ostream.write_short(value.id);
    }

}
