package MonAppliMessagerie;

/**
 * Interface definition : AC
 * 
 * @author OpenORB Compiler
 */
public abstract class ACPOA extends org.omg.PortableServer.Servant
        implements ACOperations, org.omg.CORBA.portable.InvokeHandler
{
    public AC _this()
    {
        return ACHelper.narrow(_this_object());
    }

    public AC _this(org.omg.CORBA.ORB orb)
    {
        return ACHelper.narrow(_this_object(orb));
    }

    private static String [] _ids_list =
    {
        "IDL:MonAppliMessagerie/AC:1.0"
    };

    public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte [] objectId)
    {
        return _ids_list;
    }

    public final org.omg.CORBA.portable.OutputStream _invoke(final String opName,
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler)
    {

        if (opName.equals("enregistrer")) {
                return _invoke_enregistrer(_is, handler);
        } else if (opName.equals("getCertificat")) {
                return _invoke_getCertificat(_is, handler);
        } else if (opName.equals("revoquerCertificat")) {
                return _invoke_revoquerCertificat(_is, handler);
        } else {
            throw new org.omg.CORBA.BAD_OPERATION(opName);
        }
    }

    // helper methods
    private org.omg.CORBA.portable.OutputStream _invoke_getCertificat(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;

        MonAppliMessagerie.Certificat _arg_result = getCertificat();

        _output = handler.createReply();
        MonAppliMessagerie.CertificatHelper.write(_output,_arg_result);

        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_enregistrer(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        String arg1_in = _is.read_string();
        String arg2_in = _is.read_string();
        String arg3_in = _is.read_string();

        MonAppliMessagerie.Certificat _arg_result = enregistrer(arg0_in, arg1_in, arg2_in, arg3_in);

        _output = handler.createReply();
        MonAppliMessagerie.CertificatHelper.write(_output,_arg_result);

        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_revoquerCertificat(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        MonAppliMessagerie.Certificat arg0_in = MonAppliMessagerie.CertificatHelper.read(_is);
        String arg1_in = _is.read_string();

        try
        {
            boolean _arg_result = revoquerCertificat(arg0_in, arg1_in);

            _output = handler.createReply();
            _output.write_boolean(_arg_result);

        }
        catch (MonAppliMessagerie.certif_revoque _exception)
        {
            _output = handler.createExceptionReply();
            MonAppliMessagerie.certif_revoqueHelper.write(_output,_exception);
        }
        return _output;
    }

}
