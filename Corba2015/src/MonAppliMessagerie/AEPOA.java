package MonAppliMessagerie;

/**
 * Interface definition : AE
 * 
 * @author OpenORB Compiler
 */
public abstract class AEPOA extends org.omg.PortableServer.Servant
        implements AEOperations, org.omg.CORBA.portable.InvokeHandler
{
    public AE _this()
    {
        return AEHelper.narrow(_this_object());
    }

    public AE _this(org.omg.CORBA.ORB orb)
    {
        return AEHelper.narrow(_this_object(orb));
    }

    private static String [] _ids_list =
    {
        "IDL:MonAppliMessagerie/AE:1.0"
    };

    public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte [] objectId)
    {
        return _ids_list;
    }

    public final org.omg.CORBA.portable.OutputStream _invoke(final String opName,
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler)
    {

        if (opName.equals("publier")) {
                return _invoke_publier(_is, handler);
        } else if (opName.equals("revoquer")) {
                return _invoke_revoquer(_is, handler);
        } else if (opName.equals("saveCertificat")) {
                return _invoke_saveCertificat(_is, handler);
        } else {
            throw new org.omg.CORBA.BAD_OPERATION(opName);
        }
    }

    // helper methods
    private org.omg.CORBA.portable.OutputStream _invoke_saveCertificat(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        String arg1_in = _is.read_string();
        String arg2_in = _is.read_string();
        String arg3_in = _is.read_string();
        String arg4_in = _is.read_string();

        MonAppliMessagerie.Certificat _arg_result = saveCertificat(arg0_in, arg1_in, arg2_in, arg3_in, arg4_in);

        _output = handler.createReply();
        MonAppliMessagerie.CertificatHelper.write(_output,_arg_result);

        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_revoquer(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        MonAppliMessagerie.Certificat arg0_in = MonAppliMessagerie.CertificatHelper.read(_is);
        String arg1_in = _is.read_string();
        String arg2_in = _is.read_string();

        boolean _arg_result = revoquer(arg0_in, arg1_in, arg2_in);

        _output = handler.createReply();
        _output.write_boolean(_arg_result);

        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_publier(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        MonAppliMessagerie.Certificat arg0_in = MonAppliMessagerie.CertificatHelper.read(_is);

        publier(arg0_in);

        _output = handler.createReply();

        return _output;
    }

}
