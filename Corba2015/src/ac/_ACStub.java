package ac;

import certificat.Certificat;
import certificat.CertificatHelper;

/**
 * Interface definition : AC
 * 
 * @author OpenORB Compiler
 */
public class _ACStub extends org.omg.CORBA.portable.ObjectImpl
        implements AC
{
    static final String[] _ids_list =
    {
        "IDL:MonAppliMessagerie/AC:1.0"
    };

    public String[] _ids()
    {
     return _ids_list;
    }

    private final static Class _opsClass = ac.ACOperations.class;

    /**
     * Operation getCertificat
     */
    public certificat.Certificat getCertificat()
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("getCertificat",true);
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("getCertificat",_opsClass);
                if (_so == null)
                   continue;
                ac.ACOperations _self = (ac.ACOperations) _so.servant;
                try
                {
                    return _self.getCertificat();
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation enregistrer
     */
    public certificat.Certificat enregistrer(String clePublique, String proprietaire, String dateExpiration, String usage)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("enregistrer",true);
                    _output.write_string(clePublique);
                    _output.write_string(proprietaire);
                    _output.write_string(dateExpiration);
                    _output.write_string(usage);
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("enregistrer",_opsClass);
                if (_so == null)
                   continue;
                ac.ACOperations _self = (ac.ACOperations) _so.servant;
                try
                {
                    return _self.enregistrer( clePublique,  proprietaire,  dateExpiration,  usage);
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
                ac.ACOperations _self = (ac.ACOperations) _so.servant;
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
