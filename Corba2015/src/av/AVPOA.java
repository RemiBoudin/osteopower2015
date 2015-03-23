package av;

import certificat.Certificat;
import certificat.CertificatHelper;

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

        certificat.Certificat _arg_result = getCertificatAC();

        _output = handler.createReply();
        certificat.CertificatHelper.write(_output,_arg_result);

        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_verifierRevocation(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        certificat.Certificat arg0_in = certificat.CertificatHelper.read(_is);

        String _arg_result = verifierRevocation(arg0_in);

        _output = handler.createReply();
        _output.write_string(_arg_result);

        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_revoquerCertificat(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        certificat.Certificat arg0_in = certificat.CertificatHelper.read(_is);
        String arg1_in = _is.read_string();

        boolean _arg_result = revoquerCertificat(arg0_in, arg1_in);

        _output = handler.createReply();
        _output.write_boolean(_arg_result);

        return _output;
    }

}
