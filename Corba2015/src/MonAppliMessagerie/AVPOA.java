package MonAppliMessagerie;

/**
 * Interface definition : AV
 * 
 * @author OpenORB Compiler
 */
public abstract class AVPOA extends org.omg.PortableServer.Servant
        implements AVOperations, org.omg.CORBA.portable.InvokeHandler
{
    public AV _this()
    {
        return AVHelper.narrow(_this_object());
    }

    public AV _this(org.omg.CORBA.ORB orb)
    {
        return AVHelper.narrow(_this_object(orb));
    }

    private static String [] _ids_list =
    {
        "IDL:MonAppliMessagerie/AV:1.0"
    };

    public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte [] objectId)
    {
        return _ids_list;
    }

    public final org.omg.CORBA.portable.OutputStream _invoke(final String opName,
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler)
    {

        if (opName.equals("getCertificatAC")) {
                return _invoke_getCertificatAC(_is, handler);
        } else if (opName.equals("revoquerCertificat")) {
                return _invoke_revoquerCertificat(_is, handler);
        } else if (opName.equals("verifierRevocation")) {
                return _invoke_verifierRevocation(_is, handler);
        } else {
            throw new org.omg.CORBA.BAD_OPERATION(opName);
        }
    }

    // helper methods
    private org.omg.CORBA.portable.OutputStream _invoke_getCertificatAC(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;

        MonAppliMessagerie.Certificat _arg_result = getCertificatAC();

        _output = handler.createReply();
        MonAppliMessagerie.CertificatHelper.write(_output,_arg_result);

        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_verifierRevocation(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        MonAppliMessagerie.Certificat arg0_in = MonAppliMessagerie.CertificatHelper.read(_is);

        try
        {
            verifierRevocation(arg0_in);

            _output = handler.createReply();

        }
        catch (MonAppliMessagerie.erreur_certif _exception)
        {
            _output = handler.createExceptionReply();
            MonAppliMessagerie.erreur_certifHelper.write(_output,_exception);
        }
        catch (MonAppliMessagerie.certif_revoque _exception)
        {
            _output = handler.createExceptionReply();
            MonAppliMessagerie.certif_revoqueHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_revoquerCertificat(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        MonAppliMessagerie.Certificat arg0_in = MonAppliMessagerie.CertificatHelper.read(_is);
        String arg1_in = _is.read_string();

        revoquerCertificat(arg0_in, arg1_in);

        _output = handler.createReply();

        return _output;
    }

}
