package MonAppliMessagerie;

/** 
 * Helper class for : Certificat
 *  
 * @author OpenORB Compiler
 */ 
public class CertificatHelper
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
     * Insert Certificat into an any
     * @param a an any
     * @param t Certificat value
     */
    public static void insert(org.omg.CORBA.Any a, MonAppliMessagerie.Certificat t)
    {
        a.insert_Streamable(new MonAppliMessagerie.CertificatHolder(t));
    }

    /**
     * Extract Certificat from an any
     * @param a an any
     * @return the extracted Certificat value
     */
    public static MonAppliMessagerie.Certificat extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        if (HAS_OPENORB && a instanceof org.openorb.CORBA.Any) {
            // streamable extraction. The jdk stubs incorrectly define the Any stub
            org.openorb.CORBA.Any any = (org.openorb.CORBA.Any)a;
            try {
                org.omg.CORBA.portable.Streamable s = any.extract_Streamable();
                if(s instanceof MonAppliMessagerie.CertificatHolder)
                    return ((MonAppliMessagerie.CertificatHolder)s).value;
            } catch (org.omg.CORBA.BAD_INV_ORDER ex) {
            }
            MonAppliMessagerie.CertificatHolder h = new MonAppliMessagerie.CertificatHolder(read(a.create_input_stream()));
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
     * Return the Certificat TypeCode
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
                org.omg.CORBA.StructMember []_members = new org.omg.CORBA.StructMember[8];

                _members[0] = new org.omg.CORBA.StructMember();
                _members[0].name = "proprietaire";
                _members[0].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_string);
                _members[1] = new org.omg.CORBA.StructMember();
                _members[1].name = "IOR_AV";
                _members[1].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_string);
                _members[2] = new org.omg.CORBA.StructMember();
                _members[2].name = "Num_Unique";
                _members[2].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_short);
                _members[3] = new org.omg.CORBA.StructMember();
                _members[3].name = "ValiditeDebut";
                _members[3].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_string);
                _members[4] = new org.omg.CORBA.StructMember();
                _members[4].name = "ValiditeFin";
                _members[4].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_string);
                _members[5] = new org.omg.CORBA.StructMember();
                _members[5].name = "ClePubClient";
                _members[5].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_string);
                _members[6] = new org.omg.CORBA.StructMember();
                _members[6].name = "usage";
                _members[6].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_string);
                _members[7] = new org.omg.CORBA.StructMember();
                _members[7].name = "Signature";
                _members[7].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_string);
                _tc = orb.create_struct_tc(id(),"Certificat",_members);
                _working = false;
            }
        }
        return _tc;
    }

    /**
     * Return the Certificat IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:MonAppliMessagerie/Certificat:1.0";

    /**
     * Read Certificat from a marshalled stream
     * @param istream the input stream
     * @return the readed Certificat value
     */
    public static MonAppliMessagerie.Certificat read(org.omg.CORBA.portable.InputStream istream)
    {
        MonAppliMessagerie.Certificat new_one = new MonAppliMessagerie.Certificat();

        new_one.proprietaire = istream.read_string();
        new_one.IOR_AV = istream.read_string();
        new_one.Num_Unique = istream.read_short();
        new_one.ValiditeDebut = istream.read_string();
        new_one.ValiditeFin = istream.read_string();
        new_one.ClePubClient = istream.read_string();
        new_one.usage = istream.read_string();
        new_one.Signature = istream.read_string();

        return new_one;
    }

    /**
     * Write Certificat into a marshalled stream
     * @param ostream the output stream
     * @param value Certificat value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, MonAppliMessagerie.Certificat value)
    {
        ostream.write_string(value.proprietaire);
        ostream.write_string(value.IOR_AV);
        ostream.write_short(value.Num_Unique);
        ostream.write_string(value.ValiditeDebut);
        ostream.write_string(value.ValiditeFin);
        ostream.write_string(value.ClePubClient);
        ostream.write_string(value.usage);
        ostream.write_string(value.Signature);
    }

}
