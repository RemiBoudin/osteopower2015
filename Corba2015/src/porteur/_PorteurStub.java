package porteur;

import certificat.Certificat;
import certificat.CertificatHelper;

/**
 * Interface definition : Porteur
 * 
 * @author OpenORB Compiler
 */
public class _PorteurStub extends org.omg.CORBA.portable.ObjectImpl
        implements Porteur
{
    static final String[] _ids_list =
    {
        "IDL:MonAppliMessagerie/Porteur:1.0"
    };

    public String[] _ids()
    {
     return _ids_list;
    }

    private final static Class _opsClass = porteur.PorteurOperations.class;

    /**
     * Operation getCertificatPorteur
     */
    public certificat.Certificat getCertificatPorteur()
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("getCertificatPorteur",true);
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("getCertificatPorteur",_opsClass);
                if (_so == null)
                   continue;
                porteur.PorteurOperations _self = (porteur.PorteurOperations) _so.servant;
                try
                {
                    return _self.getCertificatPorteur();
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

}
