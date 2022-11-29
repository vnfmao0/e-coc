package kr.co.haesungds.tempuri;

public class HD_ApprovalWebServiceSoapProxy implements HD_ApprovalWebServiceSoap {
  private String _endpoint = null;
  private HD_ApprovalWebServiceSoap hD_ApprovalWebServiceSoap = null;
  
  public HD_ApprovalWebServiceSoapProxy() {
    _initHD_ApprovalWebServiceSoapProxy();
  }
  
  public HD_ApprovalWebServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initHD_ApprovalWebServiceSoapProxy();
  }
  
  private void _initHD_ApprovalWebServiceSoapProxy() {
    try {
      hD_ApprovalWebServiceSoap = (new HD_ApprovalWebServiceLocator()).getHD_ApprovalWebServiceSoap();
      if (hD_ApprovalWebServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)hD_ApprovalWebServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)hD_ApprovalWebServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (hD_ApprovalWebServiceSoap != null)
      ((javax.xml.rpc.Stub)hD_ApprovalWebServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public HD_ApprovalWebServiceSoap getHD_ApprovalWebServiceSoap() {
    if (hD_ApprovalWebServiceSoap == null)
      _initHD_ApprovalWebServiceSoapProxy();
    return hD_ApprovalWebServiceSoap;
  }
  
  public String approvalSubmitXmlString(String xmlstring) throws java.rmi.RemoteException{
    if (hD_ApprovalWebServiceSoap == null)
      _initHD_ApprovalWebServiceSoapProxy();
    return hD_ApprovalWebServiceSoap.approvalSubmitXmlString(xmlstring);
  }
  
  public String approvalSubmit(ApprovalSubmitXmldom xmldom) throws java.rmi.RemoteException{
    if (hD_ApprovalWebServiceSoap == null)
      _initHD_ApprovalWebServiceSoapProxy();
    return hD_ApprovalWebServiceSoap.approvalSubmit(xmldom);
  }
  
  public String approvalCancelXmlString(String xmlstring) throws java.rmi.RemoteException{
    if (hD_ApprovalWebServiceSoap == null)
      _initHD_ApprovalWebServiceSoapProxy();
    return hD_ApprovalWebServiceSoap.approvalCancelXmlString(xmlstring);
  }
  
  public String approvalCancel(ApprovalCancelXmldom xmldom) throws java.rmi.RemoteException{
    if (hD_ApprovalWebServiceSoap == null)
      _initHD_ApprovalWebServiceSoapProxy();
    return hD_ApprovalWebServiceSoap.approvalCancel(xmldom);
  }
  
  public String approvalStatusByMisIDXmlString(String xmlstring) throws java.rmi.RemoteException{
    if (hD_ApprovalWebServiceSoap == null)
      _initHD_ApprovalWebServiceSoapProxy();
    return hD_ApprovalWebServiceSoap.approvalStatusByMisIDXmlString(xmlstring);
  }
  
  public String approvalStatusByMisID(ApprovalStatusByMisIDXmldom xmldom) throws java.rmi.RemoteException{
    if (hD_ApprovalWebServiceSoap == null)
      _initHD_ApprovalWebServiceSoapProxy();
    return hD_ApprovalWebServiceSoap.approvalStatusByMisID(xmldom);
  }
  
  
}