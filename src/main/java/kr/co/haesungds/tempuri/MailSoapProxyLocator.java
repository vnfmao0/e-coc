/**
 * MailSoapProxyLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kr.co.haesungds.tempuri;

public class MailSoapProxyLocator extends org.apache.axis.client.Service implements MailSoapProxy {

    public MailSoapProxyLocator() {
    }


    public MailSoapProxyLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MailSoapProxyLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for MailSoapProxySoap
    private String MailSoapProxySoap_address = "https://hds.haesunggroup.com/WebSite/Base/Controls/MailSoapProxy.asmx";

    public String getMailSoapProxySoapAddress() {
        return MailSoapProxySoap_address;
    }

    // The WSDD service name defaults to the port name.
    private String MailSoapProxySoapWSDDServiceName = "MailSoapProxySoap";

    public String getMailSoapProxySoapWSDDServiceName() {
        return MailSoapProxySoapWSDDServiceName;
    }

    public void setMailSoapProxySoapWSDDServiceName(String name) {
        MailSoapProxySoapWSDDServiceName = name;
    }

    public MailSoapProxySoap getMailSoapProxySoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(MailSoapProxySoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMailSoapProxySoap(endpoint);
    }

    public MailSoapProxySoap getMailSoapProxySoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            MailSoapProxySoapStub _stub = new MailSoapProxySoapStub(portAddress, this);
            _stub.setPortName(getMailSoapProxySoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMailSoapProxySoapEndpointAddress(String address) {
        MailSoapProxySoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (MailSoapProxySoap.class.isAssignableFrom(serviceEndpointInterface)) {
                MailSoapProxySoapStub _stub = new MailSoapProxySoapStub(new java.net.URL(MailSoapProxySoap_address), this);
                _stub.setPortName(getMailSoapProxySoapWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("MailSoapProxySoap".equals(inputPortName)) {
            return getMailSoapProxySoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "MailSoapProxy");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "MailSoapProxySoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("MailSoapProxySoap".equals(portName)) {
            setMailSoapProxySoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
