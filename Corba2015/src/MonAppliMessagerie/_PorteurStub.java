package MonAppliMessagerie;

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

    private final static Class _opsClass = MonAppliMessagerie.PorteurOperations.class;

    /**
     * Operation getCertificatPorteur
     */
    public MonAppliMessagerie.Certificat getCertificatPorteur()
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("getCertificatPorteur",_opsClass);
                if (_so == null)
                   continue;
                MonAppliMessagerie.PorteurOperations _self = (MonAppliMessagerie.PorteurOperations) _so.servant;
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

    /**
     * Operation afficherMessage
     */
    public void afficherMessage(String message)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("afficherMessage",true);
                    _output.write_string(message);
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("afficherMessage",_opsClass);
                if (_so == null)
                   continue;
                MonAppliMessagerie.PorteurOperations _self = (MonAppliMessagerie.PorteurOperations) _so.servant;
                try
                {
                    _self.afficherMessage( message);
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
