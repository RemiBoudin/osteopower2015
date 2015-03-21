package MonAppliMessagerie;

/**
 * Interface definition : Porteur
 * 
 * @author OpenORB Compiler
 */
public abstract class PorteurPOA extends org.omg.PortableServer.Servant
        implements PorteurOperations, org.omg.CORBA.portable.InvokeHandler
{
    public Porteur _this()
    {
        return PorteurHelper.narrow(_this_object());
    }

    public Porteur _this(org.omg.CORBA.ORB orb)
    {
        return PorteurHelper.narrow(_this_object(orb));
    }

    private static String [] _ids_list =
    {
        "IDL:MonAppliMessagerie/Porteur:1.0"
    };

    public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte [] objectId)
    {
        return _ids_list;
    }

    public final org.omg.CORBA.portable.OutputStream _invoke(final String opName,
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler)
    {

        if (opName.equals("getCertificatPorteur")) {
                return _invoke_getCertificatPorteur(_is, handler);
        } else {
            throw new org.omg.CORBA.BAD_OPERATION(opName);
        }
    }

    // helper methods
    private org.omg.CORBA.portable.OutputStream _invoke_getCertificatPorteur(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;

        MonAppliMessagerie.Certificat _arg_result = getCertificatPorteur();

        _output = handler.createReply();
        MonAppliMessagerie.CertificatHelper.write(_output,_arg_result);

        return _output;
    }

}
