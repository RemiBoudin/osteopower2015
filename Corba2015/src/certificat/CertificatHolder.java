package certificat;

/**
 * Holder class for : Certificat
 * 
 * @author OpenORB Compiler
 */
final public class CertificatHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal Certificat value
     */
    public certificat.Certificat value;

    /**
     * Default constructor
     */
    public CertificatHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public CertificatHolder(certificat.Certificat initial)
    {
        value = initial;
    }

    /**
     * Read Certificat from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = CertificatHelper.read(istream);
    }

    /**
     * Write Certificat into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        CertificatHelper.write(ostream,value);
    }

    /**
     * Return the Certificat TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return CertificatHelper.type();
    }

}
