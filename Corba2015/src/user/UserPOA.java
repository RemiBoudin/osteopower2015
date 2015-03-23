package user;

/**
 * Interface definition : User
 * 
 * @author OpenORB Compiler
 */
public abstract class UserPOA extends org.omg.PortableServer.Servant
        implements UserOperations, org.omg.CORBA.portable.InvokeHandler
{
    public User _this()
    {
        return UserHelper.narrow(_this_object());
    }

    public User _this(org.omg.CORBA.ORB orb)
    {
        return UserHelper.narrow(_this_object(orb));
    }

    private static String [] _ids_list =
    {
        "IDL:MonAppliMessagerie/User:1.0"
    };

    public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte [] objectId)
    {
        return _ids_list;
    }

    public final org.omg.CORBA.portable.OutputStream _invoke(final String opName,
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler)
    {

        if (opName.equals("afficherMessage")) {
                return _invoke_afficherMessage(_is, handler);
        } else {
            throw new org.omg.CORBA.BAD_OPERATION(opName);
        }
    }

    // helper methods
    private org.omg.CORBA.portable.OutputStream _invoke_afficherMessage(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        String arg1_in = _is.read_string();
        boolean arg2_in = _is.read_boolean();

        String _arg_result = afficherMessage(arg0_in, arg1_in, arg2_in);

        _output = handler.createReply();
        _output.write_string(_arg_result);

        return _output;
    }

}
