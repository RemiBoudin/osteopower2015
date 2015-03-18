package Convertisseur;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "Convertisseur.idl"
 * <li> <b>IDL Name</b>      ::Convertisseur::Euro
 * <li> <b>Repository Id</b> IDL:Convertisseur/Euro:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface Euro {
  ...
};
 * </pre>
 */
public abstract class EuroPOA extends org.omg.PortableServer.Servant implements 
org.omg.CORBA.portable.InvokeHandler, Convertisseur.EuroOperations {

  public Convertisseur.Euro _this () {
   return Convertisseur.EuroHelper.narrow(super._this_object());
  }

  public Convertisseur.Euro _this (org.omg.CORBA.ORB orb) {
    return Convertisseur.EuroHelper.narrow(super._this_object(orb));
  }

  public java.lang.String[] _all_interfaces (final org.omg.PortableServer.POA poa, final byte[] objectId) {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:Convertisseur/Euro:1.0"
  };

  private static java.util.Dictionary _methods = new java.util.Hashtable();

  static {
  _methods.put("_get_taux", new int[] { 0, 0 });
  _methods.put("_set_taux", new int[] { 0, 1 });
  _methods.put("_get_devise", new int[] { 0, 2 });
  _methods.put("_set_devise", new int[] { 0, 3 });
  _methods.put("toEuro", new int[] { 0, 4 });
  _methods.put("toDevise", new int[] { 0, 5 });
  _methods.put("afficherMessage", new int[] { 0, 6 });
  }

  public org.omg.CORBA.portable.OutputStream _invoke (java.lang.String opName,
                                                      org.omg.CORBA.portable.InputStream _input,
                                                      org.omg.CORBA.portable.ResponseHandler handler) {
    int[] method = (int[]) _methods.get(opName);
    if (method == null) {
      throw new org.omg.CORBA.BAD_OPERATION();
    }
    switch (method[0]) {
      case 0: {
        return Convertisseur.EuroPOA._invoke(this, method[1], _input, handler);
      }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }

  public static org.omg.CORBA.portable.OutputStream _invoke (Convertisseur.EuroOperations _self,
                                                             int _method_id,
                                                             org.omg.CORBA.portable.InputStream _input,
                                                             org.omg.CORBA.portable.ResponseHandler _handler) {
  org.omg.CORBA.portable.OutputStream _output = null;
  {
    switch (_method_id) {
    case 0: {
      double _result = _self.taux();
      _output = _handler.createReply();
      _output.write_double((double)_result);
      return _output;
    }
    case 1: {
      double arg0;
      arg0 = _input.read_double();
      _self.taux(arg0);
      _output = _handler.createReply();
      return _output;
    }
    case 2: {
      java.lang.String _result = _self.devise();
      _output = _handler.createReply();
      _output.write_string((java.lang.String)_result);
      return _output;
    }
    case 3: {
      java.lang.String arg0;
      arg0 = _input.read_string();
      _self.devise(arg0);
      _output = _handler.createReply();
      return _output;
    }
    case 4: {
      double devise;
      devise = _input.read_double();
      double _result = _self.toEuro(devise);
      _output = _handler.createReply();
      _output.write_double((double)_result);
      return _output;
    }
    case 5: {
      double euro;
      euro = _input.read_double();
      double _result = _self.toDevise(euro);
      _output = _handler.createReply();
      _output.write_double((double)_result);
      return _output;
    }
    case 6: {
      java.lang.String message;
      message = _input.read_string();
      _self.afficherMessage(message);
      _output = _handler.createReply();
      return _output;
    }
    }
    throw new org.omg.CORBA.BAD_OPERATION(2, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
  }
  }
}
