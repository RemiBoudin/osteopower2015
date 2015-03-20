package MonAppliMessagerie;

/**
 * Interface definition : User
 * 
 * @author OpenORB Compiler
 */
public class _UserStub extends org.omg.CORBA.portable.ObjectImpl
        implements User
{
    static final String[] _ids_list =
    {
        "IDL:MonAppliMessagerie/User:1.0"
    };

    public String[] _ids()
    {
     return _ids_list;
    }

    private final static Class _opsClass = MonAppliMessagerie.UserOperations.class;

    /**
     * Operation afficherMessage
     */
    public String afficherMessage(String message, boolean chiffred)
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
                    _output.write_boolean(chiffred);
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("afficherMessage",_opsClass);
                if (_so == null)
                   continue;
                MonAppliMessagerie.UserOperations _self = (MonAppliMessagerie.UserOperations) _so.servant;
                try
                {
                    return _self.afficherMessage( message,  chiffred);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

}
