package kr.co.haesungds.tempuri;

public class MailSoapProxySoapProxy implements MailSoapProxySoap {
  private String _endpoint = null;
  private MailSoapProxySoap mailSoapProxySoap = null;
  
  public MailSoapProxySoapProxy() {
    _initMailSoapProxySoapProxy();
  }
  
  public MailSoapProxySoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initMailSoapProxySoapProxy();
  }
  
  private void _initMailSoapProxySoapProxy() {
    try {
      mailSoapProxySoap = (new MailSoapProxyLocator()).getMailSoapProxySoap();
      if (mailSoapProxySoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)mailSoapProxySoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)mailSoapProxySoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (mailSoapProxySoap != null)
      ((javax.xml.rpc.Stub)mailSoapProxySoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public MailSoapProxySoap getMailSoapProxySoap() {
    if (mailSoapProxySoap == null)
      _initMailSoapProxySoapProxy();
    return mailSoapProxySoap;
  }
  
  public String mailSendXmlString(String pRequestXML) throws java.rmi.RemoteException{
    if (mailSoapProxySoap == null)
      _initMailSoapProxySoapProxy();
    return mailSoapProxySoap.mailSendXmlString(pRequestXML);
  }
  
  
}