package ae;

import certificat.Certificat;
import certificat.CertificatHelper;

/**
 * Interface definition : AE
 * 
 * @author OpenORB Compiler
 */
public class _AEStub extends org.omg.CORBA.portable.ObjectImpl
        implements AE
{
    static final String[] _ids_list =
    {
        "IDL:MonAppliMessagerie/AE:1.0"
    };

    public String[] _ids()
    {
     return _ids_list;
    }

    private final static Class _opsClass = ae.AEOperations.class;

    /**
     * Operation saveCertificat
     */
    public certificat.Certificat saveCertificat(String clepublique, String proprietaire, String mdp, String dateExpiration, String usage)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("saveCertificat",true);
                    _output.write_string(clepublique);
                    _output.write_string(proprietaire);
                    _output.write_string(mdp);
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("saveCertificat",_opsClass);
                if (_so == null)
                   continue;
                ae.AEOperations _self = (ae.AEOperations) _so.servant;
                try
                {
                    return _self.saveCertificat( clepublique,  proprietaire,  mdp,  dateExpiration,  usage);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation revoquer
     */
    public boolean revoquer(certificat.Certificat certificatPorteur, String mdp, String periode)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("revoquer",true);
                    certificat.CertificatHelper.write(_output,certificatPorteur);
                    _output.write_string(mdp);
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("revoquer",_opsClass);
                if (_so == null)
                   continue;
                ae.AEOperations _self = (ae.AEOperations) _so.servant;
                try
                {
                    return _self.revoquer( certificatPorteur,  mdp,  periode);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation publier
     */
    public void publier(certificat.Certificat certificatPorteur)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("publier",true);
                    certificat.CertificatHelper.write(_output,certificatPorteur);
                    _input = this._invoke(_output);
                    return;
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("publier",_opsClass);
                if (_so == null)
                   continue;
                ae.AEOperations _self = (ae.AEOperations) _so.servant;
                try
                {
                    _self.publier( certificatPorteur);
                    return;
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

}
