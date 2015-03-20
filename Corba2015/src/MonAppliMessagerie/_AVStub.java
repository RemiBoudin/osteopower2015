package MonAppliMessagerie;

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

    private final static Class _opsClass = MonAppliMessagerie.AVOperations.class;

    /**
     * Operation getCertificatAC
     */
    public MonAppliMessagerie.Certificat getCertificatAC()
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
                    MonAppliMessagerie.Certificat _arg_ret = MonAppliMessagerie.CertificatHelper.read(_input);
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
                MonAppliMessagerie.AVOperations _self = (MonAppliMessagerie.AVOperations) _so.servant;
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
    public void verifierRevocation(MonAppliMessagerie.Certificat certifCourant)
        throws MonAppliMessagerie.erreur_certif, MonAppliMessagerie.certif_revoque
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("verifierRevocation",true);
                    MonAppliMessagerie.CertificatHelper.write(_output,certifCourant);
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
                    if (_exception_id.equals(MonAppliMessagerie.erreur_certifHelper.id()))
                    {
                        throw MonAppliMessagerie.erreur_certifHelper.read(_exception.getInputStream());
                    }

                    if (_exception_id.equals(MonAppliMessagerie.certif_revoqueHelper.id()))
                    {
                        throw MonAppliMessagerie.certif_revoqueHelper.read(_exception.getInputStream());
                    }

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
                MonAppliMessagerie.AVOperations _self = (MonAppliMessagerie.AVOperations) _so.servant;
                try
                {
                    _self.verifierRevocation( certifCourant);
                    return;
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
    public void revoquerCertificat(MonAppliMessagerie.Certificat certificatPorteur, String periode)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("revoquerCertificat",true);
                    MonAppliMessagerie.CertificatHelper.write(_output,certificatPorteur);
                    _output.write_string(periode);
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("revoquerCertificat",_opsClass);
                if (_so == null)
                   continue;
                MonAppliMessagerie.AVOperations _self = (MonAppliMessagerie.AVOperations) _so.servant;
                try
                {
                    _self.revoquerCertificat( certificatPorteur,  periode);
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
