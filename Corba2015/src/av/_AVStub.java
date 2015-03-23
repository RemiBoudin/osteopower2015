package av;

import certificat.Certificat;
import certificat.CertificatHelper;

/**
 * Interface definition : AV
 * 
 * @author OpenORB Compiler
 */
public class _AVStub extends org.omg.CORBA.portable.ObjectImpl
        implements AV
{
    static final String[] _ids_list =
    {
        "IDL:MonAppliMessagerie/AV:1.0"
    };

    public String[] _ids()
    {
     return _ids_list;
    }

    private final static Class _opsClass = av.AVOperations.class;

    /**
     * Operation getCertificatAC
     */
    public certificat.Certificat getCertificatAC()
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("getCertificatAC",true);
                    _input = this._invoke(_output);
                    certificat.Certificat _arg_ret = certificat.CertificatHelper.read(_input);
                    return _arg_ret;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("getCertificatAC",_opsClass);
                if (_so == null)
                   continue;
                av.AVOperations _self = (av.AVOperations) _so.servant;
                try
                {
                    return _self.getCertificatAC();
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation verifierRevocation
     */
    public String verifierRevocation(certificat.Certificat certifCourant)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("verifierRevocation",true);
                    certificat.CertificatHelper.write(_output,certifCourant);
                    _input = this._invoke(_output);
                    String _arg_ret = _input.read_string();
                    return _arg_ret;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("verifierRevocation",_opsClass);
                if (_so == null)
                   continue;
                av.AVOperations _self = (av.AVOperations) _so.servant;
                try
                {
                    return _self.verifierRevocation( certifCourant);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation revoquerCertificat
     */
    public boolean revoquerCertificat(certificat.Certificat certificatPorteur, String periode)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("revoquerCertificat",true);
                    certificat.CertificatHelper.write(_output,certificatPorteur);
                    _output.write_string(periode);
                    _input = this._invoke(_output);
                    boolean _arg_ret = _input.read_boolean();
                    return _arg_ret;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("revoquerCertificat",_opsClass);
                if (_so == null)
                   continue;
                av.AVOperations _self = (av.AVOperations) _so.servant;
                try
                {
                    return _self.revoquerCertificat( certificatPorteur,  periode);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

}
