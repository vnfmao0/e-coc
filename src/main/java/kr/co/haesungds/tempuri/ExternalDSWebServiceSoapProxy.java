package kr.co.haesungds.tempuri;

public class ExternalDSWebServiceSoapProxy implements ExternalDSWebServiceSoap {
  private String _endpoint = null;
  private ExternalDSWebServiceSoap externalDSWebServiceSoap = null;
  
  public ExternalDSWebServiceSoapProxy() {
    _initExternalDSWebServiceSoapProxy();
  }
  
  public ExternalDSWebServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initExternalDSWebServiceSoapProxy();
  }
  
  private void _initExternalDSWebServiceSoapProxy() {
    try {
      externalDSWebServiceSoap = (new ExternalDSWebServiceLocator()).getExternalDSWebServiceSoap();
      if (externalDSWebServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)externalDSWebServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)externalDSWebServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (externalDSWebServiceSoap != null)
      ((javax.xml.rpc.Stub)externalDSWebServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public ExternalDSWebServiceSoap getExternalDSWebServiceSoap() {
    if (externalDSWebServiceSoap == null)
      _initExternalDSWebServiceSoapProxy();
    return externalDSWebServiceSoap;
  }
  
  public String employeeSearchByNumber(String pEmpNo) throws java.rmi.RemoteException{
    if (externalDSWebServiceSoap == null)
      _initExternalDSWebServiceSoapProxy();
    return externalDSWebServiceSoap.employeeSearchByNumber(pEmpNo);
  }
  
  public String employeeSearchByMail(String pMailAddress) throws java.rmi.RemoteException{
    if (externalDSWebServiceSoap == null)
      _initExternalDSWebServiceSoapProxy();
    return externalDSWebServiceSoap.employeeSearchByMail(pMailAddress);
  }
  
  public String employeeSearchByName(String pName) throws java.rmi.RemoteException{
    if (externalDSWebServiceSoap == null)
      _initExternalDSWebServiceSoapProxy();
    return externalDSWebServiceSoap.employeeSearchByName(pName);
  }
  
  
}